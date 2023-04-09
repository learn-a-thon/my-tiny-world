package com.yunbok.searchapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SearchApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchApiApplication.class, args);
	}

}
