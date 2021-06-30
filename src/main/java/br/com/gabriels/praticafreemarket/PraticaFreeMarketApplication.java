package br.com.gabriels.praticafreemarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PraticaFreeMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(PraticaFreeMarketApplication.class, args);
	}
}
