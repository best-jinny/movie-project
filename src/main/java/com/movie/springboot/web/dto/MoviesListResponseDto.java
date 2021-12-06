package com.movie.springboot.web.dto;

import com.movie.springboot.domain.movies.Movies;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MoviesListResponseDto {

    private Long id;
    private String title;
    private String link;
    private String image;
    private String director;
    private String actors;

    public MoviesListResponseDto(Movies entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.link = entity.getLink();
        this.image = entity.getImage();
        this.director = entity.getDirector();
        this.actors = entity.getActors();
    }


    public MoviesListResponseDto(MoviesListResponseDto moviesListResponseDto) {
    }
}
