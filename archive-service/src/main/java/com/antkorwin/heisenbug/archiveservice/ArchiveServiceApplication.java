package com.antkorwin.heisenbug.archiveservice;

import com.antkorwin.spring.commons.banner.SpringBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ArchiveServiceApplication {

    public static void main(String[] args) {
        SpringBanner.print(() -> SpringApplication.run(ArchiveServiceApplication.class, args));
    }
}
