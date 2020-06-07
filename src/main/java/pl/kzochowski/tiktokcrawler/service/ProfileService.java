package pl.kzochowski.tiktokcrawler.service;

import pl.kzochowski.tiktokcrawler.model.Profile;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public interface ProfileService {
    Profile addProfile(String profilePageUrl);

    List<Profile> getAllProfiles();

    Profile getProfileByUniqueId(String profileName);

    Optional<List<Profile>> fetchProfilesToCrawl();

    class BadRequestException extends RuntimeException {
        public BadRequestException() {
            super("Bad request. Body is null!");
        }
    }

    class ProfilePageDoesNotExistException extends RuntimeException {
        public ProfilePageDoesNotExistException(String profilePageUrl) {
            super(String.format("Profile page with url %s does not exist", profilePageUrl));
        }
    }

    class ProfileAlreadyAddedException extends RuntimeException {
        public ProfileAlreadyAddedException(String profilePageUrl) {
            super(String.format("Profile with page url %s already added", profilePageUrl));
        }
    }

    class ProfileDoesNotExistException extends RuntimeException {
        public ProfileDoesNotExistException(String profilePageUrl) {
            super(String.format("Profile with page url %s does not exist", profilePageUrl));
        }
    }

    class ProfileJsonProcessingException extends RuntimeException {
        public ProfileJsonProcessingException(String profilePageUrl) {
            super(String.format("Profile json processing exception. Page url: %s", profilePageUrl));
        }
    }

    class DatabaseDoesNotContainProfileException extends RuntimeException {
        public DatabaseDoesNotContainProfileException(String uniqueId) {
            super(String.format("Database does not contain profile with unique id: %s", uniqueId));
        }
    }

    class ProfileCreatingException extends RuntimeException {
        public ProfileCreatingException(String profilePageUrl) {
            super(String.format("Creating profile exception. Page url: %s", profilePageUrl));
        }
    }
}
