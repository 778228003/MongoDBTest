package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MongoDBTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoDBTestApplication.class, args);
    }

}