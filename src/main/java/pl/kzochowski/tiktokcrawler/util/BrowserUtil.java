package pl.kzochowski.tiktokcrawler.util;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BrowserUtil {

    private final String chromeDriverPath;
    private final String userAgentToRenderPage;

    public BrowserUtil(@Value("${webdriver.chrome.driver:driver/chromedriver}") String chromeDriverPath,
                       @Value("${userAgent.toRenderPage}") String userAgentToRenderPage) {
        this.chromeDriverPath = chromeDriverPath;
        this.userAgentToRenderPage = userAgentToRenderPage;
    }

    //todo static method?

    public ChromeDriver getChromeDriverInstance() {
        //todo add to configuration class, remove this property
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--no-zygote");
        options.addArguments("--single-process");
        options.addArguments("--keep-alive-for-test");
        options.addArguments("--headless");
        options.addArguments("user-agent=\"" + userAgentToRenderPage + "\"");
        return new ChromeDriver(options);
    }

}
