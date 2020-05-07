package pl.kzochowski.tiktokcrawler.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kzochowski.tiktokcrawler.model.PageUrl;
import pl.kzochowski.tiktokcrawler.model.Profile;
import pl.kzochowski.tiktokcrawler.service.ProfileService;
import pl.kzochowski.tiktokcrawler.service.ProfileService.BadRequestException;

import java.util.List;
import java.util.Objects;

@RestController
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/profiles")
    @ResponseStatus(HttpStatus.CREATED)
    Profile addProfile(@RequestBody PageUrl pageUrl) {
        if (Objects.isNull(pageUrl.getPageUrl()))
            throw new BadRequestException();
        return profileService.addProfile(pageUrl.getPageUrl());
    }

    @GetMapping("/profiles")
    @ResponseStatus(HttpStatus.OK)
    List<Profile> allProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/profiles/{uniqueId}")
    @ResponseStatus(HttpStatus.OK)
    Profile getProfileByUniqueId(@PathVariable("uniqueId") String uniqueId) {
        return profileService.getProfileByUniqueId(uniqueId);
    }

}
