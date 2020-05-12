package pl.kzochowski.tiktokcrawler.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagePostsDto {

    //todo refactor
    private String pageUrl;
    private List<Post> postList;

    public PagePostsDto(String pageUrl) {
        this.pageUrl = pageUrl;
    }

}
