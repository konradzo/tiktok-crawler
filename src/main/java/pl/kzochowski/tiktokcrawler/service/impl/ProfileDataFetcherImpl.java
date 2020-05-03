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
import pl.kzochowski.tiktokcrawler.service.ProfileDataFetcher;

import pl.kzochowski.tiktokcrawler.service.ProfileService.ProfilePageDoesNotExistException;
import pl.kzochowski.tiktokcrawler.service.ProfileService.ProfileJsonProcessingException;

import java.net.URI;
import java.util.Arrays;

@Service
public class ProfileDataFetcherImpl implements ProfileDataFetcher {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    public ProfileDataFetcherImpl(RestTemplate restTemplate, ObjectMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    @Override
    public JsonNode getProfileJsonInfo(String profilePageUrl) {
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

    private JsonNode fetchJson(String pageContent) throws JsonProcessingException {
        String jsonStart = "<script id=\"__NEXT_DATA__\" type=\"application/json\" crossorigin=\"anonymous\">";
        String jsonEnd = "</script><script async=\"\" data-next-page=\"/share/";
        String json = Arrays.asList(pageContent.split(jsonStart + "|\\" + jsonEnd)).get(1);
        return mapper.readTree(json);
    }

}