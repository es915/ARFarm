package com.example.arfarmPrSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ArfarmPrSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArfarmPrSiteApplication.class, args);
	}

}
