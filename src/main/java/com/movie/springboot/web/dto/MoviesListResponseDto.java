package com.movie.springboot.web.dto;

import com.movie.springboot.domain.movies.Movies;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MoviesListResponseDto {

    private String title;
    private String link;
    private String image;
    private String director;
    private String actors;

    public MoviesListResponseDto(Movies entity) {
        this.title = getTitle();
        this.link = getLink();
        this.image = getImage();
        this.director = getDirector();
        this.actors = getActors();
    }


}
