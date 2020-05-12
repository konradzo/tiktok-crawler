package pl.kzochowski.tiktokcrawler.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BrowserUtil {

    private final String chromeDriverPath;
    private final String userAgentToRenderPage;

    public BrowserUtil(@Value("${webdriver.chrome:driver/chromedriver}") String chromeDriverPath,
                       @Value("${userAgent.toRenderPage}")String userAgentToRenderPage) {
        this.chromeDriverPath = chromeDriverPath;
        this.userAgentToRenderPage = userAgentToRenderPage;
    }

}
