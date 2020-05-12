package pl.kzochowski.tiktokcrawler.service;

import pl.kzochowski.tiktokcrawler.model.Post;

import java.util.List;

public interface PostService {

    List<Post> fetchPosts(String pageUrl);

}
