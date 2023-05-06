package com.sparta.meme_nameking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MemeNameKingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemeNameKingApplication.class, args);
    }

}
