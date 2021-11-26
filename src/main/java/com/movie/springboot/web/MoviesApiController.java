package com.movie.springboot.web;

import com.movie.springboot.service.movies.MoviesService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MoviesApiController {
    private final MoviesService moviesService;

    @GetMapping("/api/v1/movies/{keyword}")
    public Object get(@PathVariable("keyword") String keyword) {
        System.out.println("*********************키워드" + keyword);
        return  moviesService.findByKeyword(keyword);
    }
}
