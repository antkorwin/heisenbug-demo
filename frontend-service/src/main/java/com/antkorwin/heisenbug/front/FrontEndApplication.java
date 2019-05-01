package com.antkorwin.heisenbug.front;

import com.antkorwin.spring.commons.banner.SpringBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * Created on 2019-04-29
 *
 * @author Korovin Anatoliy
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class FrontEndApplication {

    public static void main(String[] args) {
        SpringBanner.print(() -> SpringApplication.run(FrontEndApplication.class, args));
    }
}
