package pl.kzochowski.tiktokcrawler.service;

import pl.kzochowski.tiktokcrawler.model.Profile;

import java.util.List;

public interface ProfileService {

    Profile addProfile(String profilePageUrl);

    List<Profile> getAllProfiles();

    class ProfilePageDoesNotExistException extends RuntimeException{
        public ProfilePageDoesNotExistException(String profilePageUrl){
            super(String.format("Profile page with url %s does not exist", profilePageUrl));
        }
    }

    class ProfileAlreadyExistsException extends RuntimeException {
        public ProfileAlreadyExistsException(String profilePageUrl) {
            super(String.format("Profile with page url %s already added", profilePageUrl));
        }
    }

    class ProfileDoesNotExistException extends RuntimeException {
        public ProfileDoesNotExistException(String profilePageUrl) {
            super(String.format("Profile with page url %s does not exist", profilePageUrl));
        }
    }

    class ProfileJsonProcessingException extends RuntimeException{
        public ProfileJsonProcessingException(String profilePageUrl){
            super(String.format("Profile json processing exception. Page url: %s", profilePageUrl));
        }
    }

}
