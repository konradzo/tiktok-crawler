package pl.kzochowski.tiktokcrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;

@SpringBootApplication
public class TiktokCrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiktokCrawlerApplication.class, args);
	}



}
