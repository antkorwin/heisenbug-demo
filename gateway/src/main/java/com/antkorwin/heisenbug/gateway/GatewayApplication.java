package com.antkorwin.heisenbug.gateway;

import com.antkorwin.spring.commons.banner.SpringBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringBanner.print(() -> SpringApplication.run(GatewayApplication.class, args));
    }
}
