package com.example.weeklyrest2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WeeklyRest2Application {

    public static void main(String[] args) {
        SpringApplication.run(WeeklyRest2Application.class, args);
    }

}
