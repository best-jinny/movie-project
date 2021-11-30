package com.movie.springboot.web.dto;

import com.movie.springboot.domain.movies.Movies;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MoviesSaveRequestDto {

    private String title;
    private String link;
    private String image;
    private String director;
    private String actors;

    @Builder
    public MoviesSaveRequestDto(String title, String link, String image, String director, String actors) {
        this.title = title;
        this.link = link;
        this.image = image;
        this.director = director;
        this.actors = actors;
    }

    public Movies toEntity() {
        return Movies.builder()
                .title(title)
                .link(link)
                .image(image)
                .director(director)
                .actors(actors)
                .build();
    }


}
