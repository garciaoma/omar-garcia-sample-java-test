# BACKEND SKILLS ASSESSMENT PROJECT

### Description
As part of Clip hiring process a test project is required to accomplish requirements as per https://github.com/cesaralcancio/simple-test

### Requirements
To have this project running there are some required software and configuration as described here:
  
    - Java 8
    - Maven 3.6.3
    - Git 2.23
    - MySQL 5.7.26
To check all those requirements are available you can run the following commands:

Java

     $ java -version
       java version "1.8.0_221"
       Java(TM) SE Runtime Environment (build 1.8.0_221-b11)
       Java HotSpot(TM) 64-Bit Server VM (build 25.221-b11, mixed mode)
Maven  

    $ mvn -version
    Apache Maven 3.6.2 (40f52333136460af0dc0d7232c0dc0bcf0d9e117; 2019-08-27T15:06:16Z)
    Maven home: /Users/omargarciamagdaleno/Documents/software/apache-maven-3.6.2
    Java version: 1.8.0_221, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home/jre
    Default locale: en_MX, platform encoding: UTF-8
    OS name: "mac os x", version: "10.15.4", arch: "x86_64", family: "mac"
    
Git
    
    $ git --version
    git version 2.23.0

MySQL

    $ mysql --version
    mysql  Ver 14.14 Distrib 5.7.26, for macos10.14 (x86_64) using  EditLine wrapper

For MySQL a root user account and password should be configured as follows:

    username=root
    password=pa$$w0rd

An existing server can be used but for this purpose the file `src/main/resources/application.properties` should be edited to use the appropiate credentials.

###### IMPORTANT! PLEASE ENSURE YOU HAVE THE DATABASE CALLED `backend_db` AVAILABLE INTO MySQL SERVER

### How to download

This project is maintained into github, under https://github.com/garciaoma/omar-garcia-sample-java-test please use the following command to clone to your local:

    $ git clone git@github.com:garciaoma/omar-garcia-sample-java-test.git

And navigate to project root folder:

    $ cd omar-garcia-sample-java-test

### Running the application

This application will run over port `9095`, please ensure is free to use.
#### Developer mode
To build and run the project you can enter the following command:

    $ mvn spring-boot:run
      .   ____          _            __ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
     :: Spring Boot ::        (v2.3.1.RELEASE)
    
    2020-06-18 07:00:07.828  WARN 22603 --- [           main] o.s.boot.StartupInfoLogger               : InetAddress.getLocalHost().getHostName() took 5003 milliseconds to respond. Please verify your network configuration (macOS machines may need to add entries to /etc/hosts).
    2020-06-18 07:00:12.835  INFO 22603 --- [           main] c.assessment.backend.BackendApplication  : Starting BackendApplication on MacBook-Pro-de-Itexico.local with PID 22603 (/Users/omargarciamagdaleno/Documents/training/omar-garcia-sample-java-test/target/classes started by omargarciamagdaleno in /Users/omargarciamagdaleno/Documents/training/omar-garcia-sample-java-test)
    2020-06-18 07:00:12.836  INFO 22603 --- [           main] c.assessment.backend.BackendApplication  : No active profile set, falling back to default profiles: default
    2020-06-18 07:00:13.248  INFO 22603 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
    2020-06-18 07:00:13.289  INFO 22603 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 33ms. Found 2 JPA repository interfaces.
    2020-06-18 07:00:13.850  INFO 22603 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 9095 (http)
    2020-06-18 07:00:13.857  INFO 22603 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
    2020-06-18 07:00:13.858  INFO 22603 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.36]
    2020-06-18 07:00:13.928  INFO 22603 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
    2020-06-18 07:00:13.928  INFO 22603 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1058 ms
    2020-06-18 07:00:13.994  INFO 22603 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
    2020-06-18 07:00:14.146  INFO 22603 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
    2020-06-18 07:00:14.310  INFO 22603 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: backend]
    2020-06-18 07:00:14.365  INFO 22603 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 5.4.17.Final
    2020-06-18 07:00:14.475  INFO 22603 --- [           main] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.0.Final}
    2020-06-18 07:00:14.617  INFO 22603 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.MySQL5Dialect
    2020-06-18 07:00:15.165  INFO 22603 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
    2020-06-18 07:00:15.172  INFO 22603 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'backend'
    2020-06-18 07:00:15.450  WARN 22603 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
    2020-06-18 07:00:15.541  INFO 22603 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
    2020-06-18 07:00:15.690  INFO 22603 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 9095 (http) with context path ''
    2020-06-18 07:00:15.697  INFO 22603 --- [           main] c.assessment.backend.BackendApplication  : Started BackendApplication in 18.131 seconds (JVM running for 18.468)
    
#### Standalone mode

Under de same location run the following commands:

To build the jar file:

    $ mvn package
And to run the application

    $ java -jar target/backend-0.0.1-SNAPSHOT.jar
    
      .   ____          _            __ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
     :: Spring Boot ::        (v2.3.1.RELEASE)
    
    2020-06-18 07:03:56.814  WARN 22671 --- [           main] o.s.boot.StartupInfoLogger               : InetAddress.getLocalHost().getHostName() took 5001 milliseconds to respond. Please verify your network configuration (macOS machines may need to add entries to /etc/hosts).
    2020-06-18 07:04:01.822  INFO 22671 --- [           main] c.assessment.backend.BackendApplication  : Starting BackendApplication v0.0.1-SNAPSHOT on MacBook-Pro-de-Itexico.local with PID 22671 (/Users/omargarciamagdaleno/Documents/training/omar-garcia-sample-java-test/target/backend-0.0.1-SNAPSHOT.jar started by omargarciamagdaleno in /Users/omargarciamagdaleno/Documents/training/omar-garcia-sample-java-test)
    2020-06-18 07:04:01.823  INFO 22671 --- [           main] c.assessment.backend.BackendApplication  : No active profile set, falling back to default profiles: default
    2020-06-18 07:04:02.431  INFO 22671 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
    2020-06-18 07:04:02.493  INFO 22671 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 53ms. Found 2 JPA repository interfaces.
    2020-06-18 07:04:03.355  INFO 22671 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 9095 (http)
    2020-06-18 07:04:03.371  INFO 22671 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
    2020-06-18 07:04:03.372  INFO 22671 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.36]
    2020-06-18 07:04:03.466  INFO 22671 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
    2020-06-18 07:04:03.466  INFO 22671 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1595 ms
    2020-06-18 07:04:03.577  INFO 22671 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
    2020-06-18 07:04:03.907  INFO 22671 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
    2020-06-18 07:04:04.162  INFO 22671 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: backend]
    2020-06-18 07:04:04.950  INFO 22671 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 5.4.17.Final
    2020-06-18 07:04:05.180  INFO 22671 --- [           main] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.0.Final}
    2020-06-18 07:04:05.711  INFO 22671 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.MySQL5Dialect
    2020-06-18 07:04:06.588  INFO 22671 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
    2020-06-18 07:04:06.600  INFO 22671 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'backend'
    2020-06-18 07:04:07.088  WARN 22671 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
    2020-06-18 07:04:07.201  INFO 22671 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
    2020-06-18 07:04:07.404  INFO 22671 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 9095 (http) with context path ''
    2020-06-18 07:04:07.413  INFO 22671 --- [           main] c.assessment.backend.BackendApplication  : Started BackendApplication in 21.005 seconds (JVM running for 21.461)

After that application should be up and running.

### Consuming the services
All services exposed into this app will return to you a JSON that contains the information or description of the result of you request. 

In this section you can find sample curl commands to consume the application:

#### Add transaction
POST /transaction

    curl --location --request POST 'http://localhost:9095/transaction' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "amount": 2.34,
        "description": "This is a test",
        "date": "2020-06-08",
        "userId": 1
    }'
#### Show transaction
GET /transaction/{userId}/{transactionUUID}

    curl --location --request GET 'http://localhost:9095/transaction/1/2ed1f4af-70a8-4b06-9fff-4f1b620cccb6'
    
#### List transactions
GET /transaction/{userId}

    curl --location --request GET 'http://localhost:9095/transaction/3'
#### Sum transactions
GET /transaction/sum/{userId}

    curl --location --request GET 'http://localhost:9095/transaction/sum/3'
#### Transactions reporting
GET /transaction/reporting/{userId}

    curl --location --request GET 'http://localhost:9095/transaction/reporting/2'
#### Randon single transaction
GET /transaction/random

    curl --location --request GET 'http://localhost:9095/transaction/random'

### Database structure

The structure of the database is pretty simple, it contains User and Transaction tables only with an association between both as the following description:

    User
        id - Primary Key
        name - Full name
    Transaction
        uuid - Primary key
        amount - double
        description - varchar
        date - datetime
        user_id - Foreign key for User table
        
This application has the capacity to load automatically some test data for you, please use regular SELECT queries to check.

### Miscellaneous 

To enable a more verbose version of Hibernate logging please uncomment the following lines on `application.properties` file, this is intended for debug purposes only:

    #logging.level.org.hibernate.SQL=DEBUG
    #logging.level.org.hibernate.type=TRACE
    #spring.jpa.show-sql=true
