package com.movie.springboot.domain.movies;

import com.movie.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Movies extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String link;
    private String image;
    private String director;
    private String actors;

    @Builder
    public Movies(String title, String link, String image, String director, String actors) {
        this.title = title;
        this.link = link;
        this.image = image;
        this.director = director;
        this.actors = actors;
    }


}
