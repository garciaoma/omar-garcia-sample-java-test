package com.assessment.backend.service.impl;

import com.assessment.backend.dao.TransactionRepository;
import com.assessment.backend.dao.UserRepository;
import com.assessment.backend.dto.TransactionDTO;
import com.assessment.backend.dto.TransactionReportDTO;
import com.assessment.backend.dto.TransactionSumDTO;
import com.assessment.backend.dto.model.Transaction;
import com.assessment.backend.dto.model.User;
import com.assessment.backend.service.TransactionService;
import com.assessment.backend.util.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Double ZERO_DOUBLE = 0D;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TransactionDTO create(TransactionDTO transactionDTO) {
        return toTransactionDTO(this.transactionRepository.save(fromDTO(transactionDTO)));
    }

    @Override
    public TransactionDTO get(Long userId, UUID uuid) {
        return toTransactionDTO(transactionRepository.findByUserIdAndId(userId, uuid));
    }

    @Override
    public List<TransactionDTO> getAll(Long userId) {
        return toTransactionDTOList(transactionRepository.findByUserIdOrderByDateDesc(userId));
    }

    @Override
    public TransactionSumDTO getSumByUser(Long userId) {
        Map<User, Double> sumResult = transactionRepository.findByUserIdOrderByDateDesc(userId).stream()
                .collect(Collectors.toMap(Transaction::getUser, Transaction::getAmount, Double::sum));

        if (sumResult.isEmpty()) {
            return new TransactionSumDTO(userId, ZERO_DOUBLE);
        }
        Map.Entry<User, Double> firstResult = sumResult.entrySet().iterator().next();
        return new TransactionSumDTO(firstResult.getKey().getId(), firstResult.getValue());
    }

    @Override
    public List<TransactionReportDTO> getReport(Long userId) {
        Map<LocalDate, List<Transaction>> transactionsMap = transactionRepository.findByUserIdOrderByDateDesc(userId)
                .stream()
                .collect(Collectors.groupingBy(tx -> dateToLocalDate(tx.getDate()).with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY)), TreeMap::new, Collectors.toList()));

        Map<LocalDate, List<Transaction>> fixedTransactionsMap = populateStartOfMonthDates(transactionsMap);

        List<TransactionReportDTO> collect = fixedTransactionsMap.entrySet().stream()
                .map(week -> getTransactionReportDTO(userId, week))
                .collect(Collectors.toList());

        populateTotalAmount(collect);

        return collect;
    }

    @Override
    public TransactionDTO getRandom() {
        Random random = new Random();
        List<Transaction> transactions = transactionRepository.findAll();
        return toTransactionDTO(transactions.get(random.nextInt(transactions.size())));
    }

    private Map<LocalDate, List<Transaction>> populateStartOfMonthDates(Map<LocalDate, List<Transaction>> transactionsMap) {
        Map<LocalDate, List<Transaction>> fixedMap = new TreeMap<>();
        transactionsMap.entrySet().forEach(localDateListEntry -> {
            fixedMap.putAll(checkAndFixMonth(localDateListEntry));
        });
        return fixedMap;
    }

    private Map<LocalDate, List<Transaction>> checkAndFixMonth(Map.Entry<LocalDate, List<Transaction>> localDateListEntry) {
        Map<LocalDate, List<Transaction>> monthFixedMap = new TreeMap<>();
        LocalDate startDate = localDateListEntry.getKey();
        if (localDateListEntry.getValue().stream().anyMatch(tx -> dateToLocalDate(tx.getDate()).getMonth() != startDate.getMonth())) {
            Map<LocalDate, List<Transaction>> collect = localDateListEntry.getValue().stream().collect(Collectors.groupingBy(tx -> dateToLocalDate(tx.getDate()).with(TemporalAdjusters.firstDayOfMonth())));
            if (collect.size() == 1) {
                Map.Entry<LocalDate, List<Transaction>> item = collect.entrySet().iterator().next();
                monthFixedMap.put(item.getKey(), item.getValue());
            } else if (collect.size() > 1 ){
                Map.Entry<LocalDate, List<Transaction>> item = collect.entrySet().iterator().next();
                monthFixedMap.put(localDateListEntry.getKey(), item.getValue());
                item = collect.entrySet().iterator().next();
                monthFixedMap.put(item.getKey(), item.getValue());
            }
        } else {
            monthFixedMap.put(startDate, localDateListEntry.getValue());
        }
        return monthFixedMap;
    }

    private void populateTotalAmount(List<TransactionReportDTO> collect) {
        BigDecimal counter = new BigDecimal(BigInteger.ZERO);

        for (TransactionReportDTO transactionReportDTO : collect) {
            transactionReportDTO.setTotalAmount(counter);
            counter = counter.add(transactionReportDTO.getAmount());
        }
    }

    private TransactionReportDTO getTransactionReportDTO(Long userId, Map.Entry<LocalDate, List<Transaction>> week) {
        TransactionReportDTO transactionReportDTO = new TransactionReportDTO();
        transactionReportDTO.setUserId(userId);
        transactionReportDTO.setAmount(getAmountSum(week.getValue()));
        transactionReportDTO.setWeekStart(getDateString(week.getKey()));
        transactionReportDTO.setWeekEnd(getDateString(getWeekEndDate(week.getKey())));
        transactionReportDTO.setQuantity((long) week.getValue().size());
        return transactionReportDTO;
    }

    private String getDateString(LocalDate localDate) {
        return localDate + " " + localDate.getDayOfWeek();
    }

    private LocalDate getWeekEndDate(LocalDate weekStart) {
        LocalDate weekEndDate = weekStart.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        if (weekStart.getMonthValue() != weekEndDate.getMonthValue()) {
            weekEndDate = weekStart.with(TemporalAdjusters.lastDayOfMonth());
        }
        return weekEndDate;
    }

    private BigDecimal getAmountSum(List<Transaction> transactions) {
        return BigDecimal.valueOf(transactions.stream().mapToDouble(Transaction::getAmount).sum());
    }

    private LocalDate dateToLocalDate(Date date) {
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    private List<TransactionDTO> toTransactionDTOList(List<Transaction> transactions) {
        return transactions.stream().map(this::toTransactionDTO).collect(Collectors.toList());
    }

    private TransactionDTO toTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setTransactionId(transaction.getId());
            transactionDTO.setAmount(transaction.getAmount());
            transactionDTO.setDescription(transaction.getDescription());
            transactionDTO.setDate(transaction.getDate());
            transactionDTO.setUserId(transaction.getUser().getId());
        return transactionDTO;
    }

    private Transaction fromDTO(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setDate(transactionDTO.getDate());

        Optional<User> optionalUser = getUser(transactionDTO.getUserId());
        if (optionalUser.isPresent()) {
            transaction.setUser(optionalUser.get());
        } else {
            throw new UserNotFoundException("The user doesn't exist");
        }
        return transaction;
    }

    private Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }
}
