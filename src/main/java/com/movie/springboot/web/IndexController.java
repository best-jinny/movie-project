package com.movie.springboot.web;

import com.movie.springboot.config.auth.LoginUser;
import com.movie.springboot.config.auth.dto.SessionUser;
import com.movie.springboot.service.movies.MoviesService;
import com.movie.springboot.service.myList.MyListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final MyListService myListService;
    private final MoviesService moviesService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/search-result")
    public String searchResult() {
        return "search-result";
    }

    @GetMapping("/my-list")
    public String myList(Model model, @LoginUser SessionUser user) {

        model.addAttribute("userName", user.getName());
        model.addAttribute("movies", moviesService.makeList(user.getId()));

        return "my-list";
    }
}
