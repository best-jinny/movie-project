package com.movie.springboot.web;

import com.movie.springboot.config.auth.LoginUser;
import com.movie.springboot.config.auth.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

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
}
