package pl.kzochowski.tiktokcrawler.service;

import pl.kzochowski.tiktokcrawler.model.Post;

import java.util.List;

public interface PostService {

    List<Post> fetchPosts(String pageUrl);

    class LoadingPageException extends RuntimeException{
        public LoadingPageException(String pageUrl){
            super(String.format("Error occurred during loading page: %s", pageUrl));
        }
    }

}
