package ru.usikov.crud_test_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CrudTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudTestTaskApplication.class, args);
    }

}
