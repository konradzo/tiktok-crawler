package pl.kzochowski.tiktokcrawler.model;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Builder
@Getter
public class Post {

    private String postId;
    private String message;
    private ZonedDateTime timestamp;
    private String uniqueId;
    private String userId;
    private String videoUrl;
    private Integer duration;
    private Integer likes;
    private Integer shares;
    private Integer comments;
    private Integer views;
    private String musicName;
    private String musicAuthorName;

}
