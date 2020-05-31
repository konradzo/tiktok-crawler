package pl.kzochowski.tiktokcrawler.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagePostsDto {

    //todo refactor
    private final List<Profile> profiles;
    private List<Post> postList;

    public PagePostsDto(List<Profile> profiles) {
        this.profiles = profiles;
    }

}
