package br.com.challengeibge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ChallengeibgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeibgeApplication.class, args);
	}

}
