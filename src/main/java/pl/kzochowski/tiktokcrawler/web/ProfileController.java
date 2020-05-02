package pl.kzochowski.tiktokcrawler.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kzochowski.tiktokcrawler.model.PageUrl;
import pl.kzochowski.tiktokcrawler.model.Profile;
import pl.kzochowski.tiktokcrawler.service.ProfileService;

import java.util.List;

@RestController
public class ProfileController {

   private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/profile")
    @ResponseStatus(HttpStatus.CREATED)
    Profile addProfile(@RequestBody PageUrl pageUrl){
        return profileService.addProfile(pageUrl.getPageUrl());
    }

    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    List<Profile> allProfiles(){
       return profileService.getAllProfiles();
    }

}
