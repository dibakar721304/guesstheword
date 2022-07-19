package com.dibakar.games.guesstheword;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GuessTheWordApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuessTheWordApplication.class, args);
    }
}
