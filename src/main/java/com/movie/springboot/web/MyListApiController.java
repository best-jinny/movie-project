package com.movie.springboot.web;

import com.movie.springboot.config.auth.LoginUser;
import com.movie.springboot.config.auth.dto.SessionUser;
import com.movie.springboot.domain.myList.MyList;
import com.movie.springboot.domain.myList.MyListRepository;
import com.movie.springboot.service.movies.MoviesService;
import com.movie.springboot.service.myList.MyListService;
import com.movie.springboot.web.dto.MoviesSaveRequestDto;
import com.movie.springboot.web.dto.MyListCommentSaveRequestDto;
import com.movie.springboot.web.dto.MyListResponseDto;
import com.movie.springboot.web.dto.MyListSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MyListApiController {

    private final MoviesService moviesService;
    private final MyListService myListService;
    private final MyListRepository myListRepository;

    @PostMapping("/api/v1/myList")
    public Long save(@RequestBody MoviesSaveRequestDto requestDto, @LoginUser SessionUser user) {

        // 영화 ID
        Long movieId = moviesService.save(requestDto);

        // User ID
        Long userId = user.getId();

        MyListSaveRequestDto dto = new MyListSaveRequestDto(userId, movieId);

        return myListService.save(dto);

    }

    @DeleteMapping("/api/v1/myList/{id}")
    public Long delete(@PathVariable Long id, @LoginUser SessionUser user) {
        // User ID
        Long userId = user.getId();

        MyList myList = myListService.findByUserIdAndMovieId(userId, id);

        myListService.delete(myList.getId());
        return myList.getId();
    }

    @PostMapping("/api/v1/myList/comment")
    public String saveComment(@RequestParam Long movieId, @RequestParam String comment, @LoginUser SessionUser user) {
        // User ID
        Long userId = user.getId();

        MyList myList = myListRepository.findByUserIdAndMovieId(userId, movieId);

        myList.updateComment(comment);


        return "redirect:my-list";
    }

}
