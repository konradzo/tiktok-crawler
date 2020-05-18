package pl.kzochowski.tiktokcrawler.service;

import com.fasterxml.jackson.databind.JsonNode;
import pl.kzochowski.tiktokcrawler.model.Post;

import java.util.List;

public interface PageDataFetcher {

    JsonNode getProfileJsonInfo(String profilePageUrl);

    List<String> fetchVideoUrlList(String pageUrl, String loadedPageHtml);

    Post fetchVideoData(String videoUrl);

}
