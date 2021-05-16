package com.bookvault.bookvault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.bookvault", "repository", "entity", "mapper", "service", "com.bookvault.bookvault.web"})
@EnableJpaRepositories(basePackages = "repository")
@EntityScan(basePackages = "entity")
public class BookvaultApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookvaultApplication.class, args);
    }

}
