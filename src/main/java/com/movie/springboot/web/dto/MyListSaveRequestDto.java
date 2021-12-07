package com.movie.springboot.web.dto;

import com.movie.springboot.domain.myList.MyList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MyListSaveRequestDto {

    private Long userId;
    private Long movieId;

    @Builder
    public MyListSaveRequestDto(Long userId, Long movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public MyList toEntity() {
        return MyList.builder()
                .userId(userId)
                .movieId(movieId)
                .build();
    }

}
