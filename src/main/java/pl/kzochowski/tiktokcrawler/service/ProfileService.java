package pl.kzochowski.tiktokcrawler.service;

public interface ProfileService {

    void addProfile(String profilePageUrl);


    class ProfileAlreadyExistsException extends RuntimeException {
        ProfileAlreadyExistsException(String profilePageUrl) {
            super(String.format("Profile with page url %s already added", profilePageUrl));
        }
    }

    class ProfileDoesNotExistException extends RuntimeException {
        ProfileDoesNotExistException(String profilePageUrl) {
            super(String.format("Profile with page url %s does not exist", profilePageUrl));
        }
    }

}
