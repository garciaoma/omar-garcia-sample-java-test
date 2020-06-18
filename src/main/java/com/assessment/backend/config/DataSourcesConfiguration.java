package com.assessment.backend.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "backendEntityManagerFactory",
        transactionManagerRef = "backendTransactionManager",
        basePackages = "com.assessment.backend.dao"
)
public class DataSourcesConfiguration {

    @Bean(name = "backendProperties")
    @ConfigurationProperties("assessment.backend")
    public Properties backendProperties() {
        return new Properties();
    }

    @Bean(name = "backendDataSource")
    public HikariDataSource dataSource() {
        HikariConfig config = new HikariConfig(backendProperties());
        return new HikariDataSource(config);
    }

    @PersistenceContext(unitName = "backend")
    @Bean(name = "backendEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean backendEntityManagerFactory(@Qualifier("backendDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.assessment.backend.dto.model"});
        em.setPersistenceUnitName("backend");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getJPAProperties());
        return em;
    }

    @Bean(name = "backendTransactionManager")
    public PlatformTransactionManager backendTransactionManager(
            @Qualifier("backendEntityManagerFactory") EntityManagerFactory backendEntityManagerFactory) {
        return new JpaTransactionManager(backendEntityManagerFactory);
    }

    private Properties getJPAProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }
}
