package com.echocano.seek.challenge;

import com.echocano.seek.challenge.infrastructure.spring.oauth.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class SeekChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeekChallengeApplication.class, args);
    }

}
