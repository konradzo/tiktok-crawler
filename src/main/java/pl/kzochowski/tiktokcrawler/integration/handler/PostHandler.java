package pl.kzochowski.tiktokcrawler.integration.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import pl.kzochowski.tiktokcrawler.model.PagePostsDto;
import pl.kzochowski.tiktokcrawler.model.Post;
import pl.kzochowski.tiktokcrawler.service.PostService;

import java.util.List;

@Slf4j
@Component
public class PostHandler implements GenericHandler<PagePostsDto> {

    private final PostService postService;

    public PostHandler(PostService postService) {
        this.postService = postService;
    }

    @Override
    public Object handle(PagePostsDto pagePostsDto, MessageHeaders messageHeaders) {
        final List<Post> posts = postService.fetchPosts(pagePostsDto.getProfiles());
        pagePostsDto.setPostList(posts);
        log.info("Number of fetched posts: {}", posts.size());
        return pagePostsDto;
    }

}
