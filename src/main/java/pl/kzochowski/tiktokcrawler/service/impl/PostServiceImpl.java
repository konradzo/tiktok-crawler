package pl.kzochowski.tiktokcrawler.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kzochowski.tiktokcrawler.model.Post;
import pl.kzochowski.tiktokcrawler.service.PostService;

import java.util.List;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    @Override
    public List<Post> fetchPosts(String pageUrl) {
        return null;
    }

}
