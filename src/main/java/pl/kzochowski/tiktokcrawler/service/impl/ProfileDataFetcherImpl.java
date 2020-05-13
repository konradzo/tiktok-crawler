package pl.kzochowski.tiktokcrawler.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.kzochowski.tiktokcrawler.model.Post;
import pl.kzochowski.tiktokcrawler.service.PageDataFetcher;

import pl.kzochowski.tiktokcrawler.service.PostService;
import pl.kzochowski.tiktokcrawler.service.ProfileService.ProfilePageDoesNotExistException;
import pl.kzochowski.tiktokcrawler.service.ProfileService.ProfileJsonProcessingException;

import java.net.URI;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ProfileDataFetcherImpl implements PageDataFetcher {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    public ProfileDataFetcherImpl(RestTemplate restTemplate, ObjectMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    @Override
    public JsonNode getProfileJsonInfo(String profilePageUrl) {

        //todo refactor - user agent from properties
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("User-Agent", "Mozilla /5.0 (Compatible MSIE 9.0;Windows NT 6.1;WOW64; Trident/5.0)");
        HttpEntity entity = new HttpEntity(httpHeaders);
        try {
            ResponseEntity<String> response = restTemplate.exchange(URI.create(profilePageUrl), HttpMethod.GET, entity, String.class);
            return fetchJson(response.getBody());
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProfilePageDoesNotExistException(profilePageUrl);
        } catch (JsonProcessingException e) {
            throw new ProfileJsonProcessingException(profilePageUrl);
        }
    }

    @Override
    public List<String> fetchVideoUrlList(String loadedPageHtml) {
        return null;
    }

    @Override
    public Post fetchVideoData(String videoUrl) {
        //todo change to make all list at once
        String jsonStart = "<script id=\"__NEXT_DATA__\" type=\"application/json\" crossorigin=\"anonymous\">";
        String jsonEnd = "</script><script async=\"\" data-next-page=\"/share/video\" ";
        ResponseEntity<String> response = restTemplate.exchange(URI.create(videoUrl), HttpMethod.GET, buildHeader(), String.class);
        String body = response.getBody();
        String postJson = Arrays.asList(body.split(jsonStart + "|\\" + jsonEnd)).get(1);
        try {
            JsonNode mainPostNode = mapper.readTree(postJson);
            return fillPostObject(videoUrl, mainPostNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new PostService.PostProcessingException(videoUrl);
        }
    }

    private JsonNode fetchJson(String pageContent) throws JsonProcessingException {
        String jsonStart = "<script id=\"__NEXT_DATA__\" type=\"application/json\" crossorigin=\"anonymous\">";
        String jsonEnd = "</script><script async=\"\" data-next-page=\"/share/";
        String json = Arrays.asList(pageContent.split(jsonStart + "|\\" + jsonEnd)).get(1);
        return mapper.readTree(json);
    }

    private HttpEntity buildHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        //todo refactor
        httpHeaders.set("User-Agent", "Mozilla /5.0 (Compatible MSIE 9.0;Windows NT 6.1;WOW64; Trident/5.0)");
        return new HttpEntity(httpHeaders);
    }

    private Post fillPostObject(String videoUrl, JsonNode mainNode) {
        JsonNode itemInfosNode = mainNode.path("props").path("pageProps").path("videoData").path("itemInfos");
        JsonNode musicInfosNode = mainNode.path("props").path("pageProps").path("videoData").path("musicInfos");
        String uniqueId = mainNode.path("props").path("pageProps").path("videoData").path("authorInfos").path("uniqueId").asText();
        String userId = mainNode.path("props").path("pageProps").path("videoData").path("authorInfos").path("userId").asText();
        String postId = itemInfosNode.path("id").asText();
        String message = itemInfosNode.path("text").asText();
        String commentCount = itemInfosNode.path("commentCount").asText();
        String likeCount = itemInfosNode.path("diggCount").asText();
        String shareCount = itemInfosNode.path("shareCount").asText();
        String viewCount = itemInfosNode.path("playCount").asText();
        String musicName = musicInfosNode.path("musicName").asText();
        String musicAuthorName = musicInfosNode.path("authorName").asText();
        ZonedDateTime timestamp = creationTime(Long.parseLong(itemInfosNode.path("createTime").asText()));
        return Post.builder()
                .postId(postId)
                .uniqueId(uniqueId)
                .userId(userId)
                .message(message)
                .likes(Integer.parseInt(likeCount))
                .comments(Integer.parseInt(commentCount))
                .shares(Integer.parseInt(shareCount))
                .views(Integer.parseInt(viewCount))
                .videoUrl(videoUrl)
                .timestamp(timestamp)
                .musicName(musicName)
                .musicAuthorName(musicAuthorName)
                .build();
    }

    private ZonedDateTime creationTime(Long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        return instant.atZone(ZoneId.of("Europe/Warsaw"));
    }

}
