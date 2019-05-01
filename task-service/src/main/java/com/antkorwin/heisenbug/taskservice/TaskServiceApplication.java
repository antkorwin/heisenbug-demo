package com.antkorwin.heisenbug.taskservice;


import com.antkorwin.spring.commons.banner.SpringBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TaskServiceApplication {

    public static void main(String[] args) {
        SpringBanner.print(() -> SpringApplication.run(TaskServiceApplication.class, args));
    }
}
