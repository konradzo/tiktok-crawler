package pl.kzochowski.tiktokcrawler.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.kzochowski.tiktokcrawler.service.ProfileDataFetcher;

import pl.kzochowski.tiktokcrawler.service.ProfileService;
import pl.kzochowski.tiktokcrawler.service.ProfileService.ProfilePageDoesNotExistException;

import java.net.URI;

@Service
public class ProfileDataFetcherImpl implements ProfileDataFetcher {

    private final RestTemplate restTemplate;

    public ProfileDataFetcherImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String fetchProfileJsonInfo(String profilePageUrl) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("User-Agent", "Mozilla /5.0 (Compatible MSIE 9.0;Windows NT 6.1;WOW64; Trident/5.0)");
        HttpEntity entity = new HttpEntity(httpHeaders);
        try {
            ResponseEntity<String> response = restTemplate.exchange(URI.create(profilePageUrl), HttpMethod.GET, entity, String.class);
        }catch (HttpClientErrorException.NotFound e){
            throw new ProfilePageDoesNotExistException(profilePageUrl);
        }
        return null;
    }

}
