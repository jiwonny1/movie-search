package dev.moviesearch.app.post.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class PostDto {
    private int contentId;
    private String title;
    private Date releaseDate;
    private String posterPath;
    private String originalLanguage;
    private BigDecimal popularity;
    private BigDecimal voteAverage;
    private int voteCount;
}
