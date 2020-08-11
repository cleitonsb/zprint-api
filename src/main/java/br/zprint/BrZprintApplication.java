package br.zprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EntityScan(basePackages = {"br.zprint.model"})
@EnableJpaRepositories(basePackages = {"br.zprint.repository"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
public class BrZprintApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrZprintApplication.class, args);
    }

}