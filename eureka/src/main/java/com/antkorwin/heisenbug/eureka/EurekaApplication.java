package com.antkorwin.heisenbug.eureka;

import com.antkorwin.spring.commons.banner.SpringBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

    public static void main(String[] args) {
        SpringBanner.print(() -> SpringApplication.run(EurekaApplication.class, args));
    }
}
