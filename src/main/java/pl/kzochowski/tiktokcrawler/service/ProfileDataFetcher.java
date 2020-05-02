package pl.kzochowski.tiktokcrawler.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface ProfileDataFetcher {

    JsonNode getProfileJsonInfo(String profilePageUrl);

}
