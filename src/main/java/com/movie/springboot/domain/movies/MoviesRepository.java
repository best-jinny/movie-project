package com.movie.springboot.domain.movies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MoviesRepository extends JpaRepository<Movies, Long> {

    @Query("SELECT m.id FROM Movies m WHERE m.link = :link")
    Long findByLink(@Param("link") String link);

}
