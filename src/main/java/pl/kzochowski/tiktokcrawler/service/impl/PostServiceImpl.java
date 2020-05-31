package pl.kzochowski.tiktokcrawler.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;
import pl.kzochowski.tiktokcrawler.model.Post;
import pl.kzochowski.tiktokcrawler.model.Profile;
import pl.kzochowski.tiktokcrawler.service.PostService;
import pl.kzochowski.tiktokcrawler.service.PageDataFetcher;
import pl.kzochowski.tiktokcrawler.util.BrowserUtil;

import java.util.ArrayList;
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
    public List<Post> fetchPosts(List<Profile> profiles) {
        ChromeDriver driver = browserUtil.getChromeDriverInstance();
        List<String> videoUrlList = new ArrayList<>();
        List<Post> results = new ArrayList<>();

        try {
            profiles.forEach(profile -> {
                String loadedPageHtml = "";
                try {
                    waitToRenderPage(driver);
                    driver.get(profile.getProfilePageUrl());

                    waitToRenderPage(driver);

                    WebElement element = driver.findElementById("main");
                    loadedPageHtml = element.getAttribute("innerHTML");
                    if (loadedPageHtml.isEmpty())
                        throw new LoadingPageException(profile.getProfilePageUrl());
                    videoUrlList.addAll(dataFetcher.fetchVideoUrlList(profile.getProfilePageUrl(), loadedPageHtml));
                } catch (LoadingPageException e) {
                    e.printStackTrace();
                }
            });
        } finally {
            driver.close();
        }

        videoUrlList.forEach(videoUrl -> {
            try {
                results.add(dataFetcher.fetchVideoData(videoUrl));
            } catch (Exception e) {
                log.error("Post processing error. Message: {}", e.getMessage());
            }
        });

        return results;
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
