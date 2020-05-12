package pl.kzochowski.tiktokcrawler.integration.source;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import pl.kzochowski.tiktokcrawler.model.PagePostsDto;
import pl.kzochowski.tiktokcrawler.model.Profile;
import pl.kzochowski.tiktokcrawler.service.ProfileService;

import java.util.Optional;

@Slf4j
@Component
public class PageSource implements MessageSource {

    private final ProfileService profileService;

    public PageSource(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public Message<PagePostsDto> receive() {
        Optional<Profile> profile = profileService.fetchProfileToCrawl();
        if (!profile.isPresent()) {
            log.info("No profile to crawl!");
            return null;
        }

        PagePostsDto dto = new PagePostsDto(profile.get().getProfilePageUrl());
        return MessageBuilder.withPayload(dto).build();
    }

}
