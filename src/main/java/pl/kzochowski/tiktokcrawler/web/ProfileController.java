package pl.kzochowski.tiktokcrawler.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.kzochowski.tiktokcrawler.model.Profile;
import pl.kzochowski.tiktokcrawler.service.ProfileService;

@RestController
public class ProfileController {

   private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/profile")
    @ResponseStatus(HttpStatus.CREATED)
    Profile addProfile(@RequestBody PageUrl pageUrl){
        return profileService.addProfile(pageUrl.pageUrl);
    }

    @Setter
    @Getter
    static class PageUrl{
        private String pageUrl;
    }

}
