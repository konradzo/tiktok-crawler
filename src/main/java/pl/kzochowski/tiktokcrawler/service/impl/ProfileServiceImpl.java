package pl.kzochowski.tiktokcrawler.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import pl.kzochowski.tiktokcrawler.model.Profile;
import pl.kzochowski.tiktokcrawler.repository.ProfileRepository;
import pl.kzochowski.tiktokcrawler.service.ProfileDataFetcher;
import pl.kzochowski.tiktokcrawler.service.ProfileService;
import pl.kzochowski.tiktokcrawler.util.ControllerExceptionHandler;
import pl.kzochowski.tiktokcrawler.util.ControllerExceptionHandler.*;

import java.util.List;
import java.util.Optional;

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
        Optional<Profile> temp = repository.findByProfilePageUrl(profilePageUrl);
        if (temp.isPresent())
            throw new ProfileAlreadyAddedException(profilePageUrl);
        JsonNode profileNode = profileDataFetcher.getProfileJsonInfo(profilePageUrl);
        Profile newProfile = createNewProfile(profilePageUrl, profileNode);
        return repository.save(newProfile);
    }

    @Override
    public List<Profile> getAllProfiles() {
        return repository.findAll();
    }

    @Override
    public Profile getProfileByUniqueId(String uniqueId) {
        return Optional.ofNullable(repository.findByUniqueId(uniqueId))
                .orElseThrow(() -> new DatabaseDoesNotContainProfileException(uniqueId));
    }

    private Profile createNewProfile(String profilePageUrl, JsonNode node) {
        try {
            JsonNode mainInfoNode = node.path("props").path("pageProps").path("userData");
            String userId = mainInfoNode.path("userId").asText();
            String nickname = mainInfoNode.path("nickname").asText();
            String uniqueId = mainInfoNode.path("uniqueId").asText();
            return Profile.builder()
                    .userId(userId)
                    .nickname(nickname)
                    .profilePageUrl(profilePageUrl)
                    .uniqueId(uniqueId)
                    .build();
        } catch (Exception e) {
            throw new ProfileCreatingException(profilePageUrl);
        }
    }

}
