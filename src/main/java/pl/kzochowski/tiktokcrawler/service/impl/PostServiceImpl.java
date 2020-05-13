package pl.kzochowski.tiktokcrawler.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;
import pl.kzochowski.tiktokcrawler.model.Post;
import pl.kzochowski.tiktokcrawler.service.PostService;
import pl.kzochowski.tiktokcrawler.service.PageDataFetcher;
import pl.kzochowski.tiktokcrawler.util.BrowserUtil;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private final BrowserUtil browserUtil;
    private final PageDataFetcher dataFetcher;

    public PostServiceImpl(BrowserUtil browserUtil, PageDataFetcher dataFetcher) {
        this.browserUtil = browserUtil;
        this.dataFetcher = dataFetcher;
    }

    @Override
    public List<Post> fetchPosts(String pageUrl) {
        ChromeDriver driver = browserUtil.getChromeDriverInstance();
        WebElement element;
        String loadedPageHtml = "";

        try {
            driver.get(pageUrl);
            waitToRenderPage(driver);

            element = driver.findElementById("main");
            loadedPageHtml = element.getAttribute("innerHTML");
            //todo handle exception
            if (loadedPageHtml.isEmpty())
                throw new LoadingPageException(pageUrl);
        } catch (LoadingPageException e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }

        List<String> videoUrlList = dataFetcher.fetchVideoUrlList(loadedPageHtml);
        return videoUrlList.stream().map(dataFetcher::fetchVideoData).collect(Collectors.toList());
    }

    private void waitToRenderPage(ChromeDriver driver) {
        //todo something else to wait several seconds?
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
