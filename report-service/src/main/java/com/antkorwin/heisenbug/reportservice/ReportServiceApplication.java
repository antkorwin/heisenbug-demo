package com.antkorwin.heisenbug.reportservice;

import com.antkorwin.spring.commons.banner.SpringBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class ReportServiceApplication {

    public static void main(String[] args) {
        SpringBanner.print(()-> SpringApplication.run(ReportServiceApplication.class, args));
    }
}
