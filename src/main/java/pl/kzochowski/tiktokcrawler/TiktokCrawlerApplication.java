package pl.kzochowski.tiktokcrawler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.PollerSpec;
import pl.kzochowski.tiktokcrawler.integration.source.PageSource;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class TiktokCrawlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiktokCrawlerApplication.class, args);
    }


    @Bean
    public IntegrationFlow fetchingPostsFlow(@Value("${crawl.delay: 10}") int delayInSeconds,
                                             PageSource source) {

        return IntegrationFlows.from(source, e -> e.poller(p -> {
            PollerSpec pollerSpec = p.fixedDelay(delayInSeconds, TimeUnit.SECONDS);
            return pollerSpec;
        }))
                .get();
    }

}
