package com.movie.springboot.web;

import com.movie.springboot.config.auth.LoginUser;
import com.movie.springboot.config.auth.dto.SessionUser;
import com.movie.springboot.service.movies.MoviesService;
import com.movie.springboot.web.dto.MoviesSaveRequestDto;
import com.movie.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MoviesApiController {
    private final MoviesService moviesService;

    @GetMapping("/api/v1/movies/{keyword}")
    public Object get(@PathVariable("keyword") String keyword) {
        System.out.println("*********************키워드" + keyword);
        return  moviesService.findByKeyword(keyword);
    }

//    @PostMapping("/api/v1/movies")
//    public Long save(@RequestBody MoviesSaveRequestDto requestDto, @LoginUser SessionUser user) {
//        System.out.println("@@@@@@@@@@@@@@@@@@@@email : " + user.getEmail());
//        return moviesService.save(requestDto);
//    }
}
