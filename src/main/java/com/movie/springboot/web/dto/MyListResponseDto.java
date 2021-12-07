package com.movie.springboot.web.dto;

import com.movie.springboot.domain.myList.MyList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyListResponseDto {

    private Long id;
    private Long userId;
    private Long movieId;

    public MyListResponseDto(MyList entity) {
        this.id = entity.getId();
        this.userId = entity.getId();
        this.movieId = entity.getMovieId();
    }
}
