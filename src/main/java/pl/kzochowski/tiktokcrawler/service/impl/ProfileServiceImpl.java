package pl.kzochowski.tiktokcrawler.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import pl.kzochowski.tiktokcrawler.model.Profile;
import pl.kzochowski.tiktokcrawler.repository.ProfileRepository;
import pl.kzochowski.tiktokcrawler.service.ProfileDataFetcher;
import pl.kzochowski.tiktokcrawler.service.ProfileService;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository repository;
    private final ProfileDataFetcher profileDataFetcher;

    public ProfileServiceImpl(ProfileRepository repository, ProfileDataFetcher profileDataFetcher) {
        this.repository = repository;
        this.profileDataFetcher = profileDataFetcher;
    }

    @Override
    public Profile addProfile(String profilePageUrl) {
        Profile newProfile = new Profile();
        JsonNode profileNode = profileDataFetcher.getProfileJsonInfo(profilePageUrl);
        return newProfile;
    }

    @Override
    public List<Profile> getAllProfiles() {
        return repository.findAll();
    }

}
