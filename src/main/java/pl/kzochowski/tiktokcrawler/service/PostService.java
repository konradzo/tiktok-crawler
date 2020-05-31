package pl.kzochowski.tiktokcrawler.service;

import pl.kzochowski.tiktokcrawler.model.Post;
import pl.kzochowski.tiktokcrawler.model.Profile;

import java.util.List;

public interface PostService {
    List<Post> fetchPosts(List<Profile> profiles);

    class LoadingPageException extends RuntimeException {
        public LoadingPageException(String pageUrl) {
            super(String.format("Error occurred during loading page: %s", pageUrl));
        }
    }

    class PostProcessingException extends RuntimeException {
        public PostProcessingException(String postUrl) {
            super(String.format("Post processing error. Url: %s", postUrl));
        }
    }
}
