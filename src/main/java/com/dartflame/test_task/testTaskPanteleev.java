package com.dartflame.test_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class testTaskPanteleev {

    public static void main(String[] args) {
        SpringApplication.run(testTaskPanteleev.class, args);
    }

}
