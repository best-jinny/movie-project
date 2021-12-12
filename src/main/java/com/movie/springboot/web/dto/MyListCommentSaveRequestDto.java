package com.movie.springboot.web.dto;

import com.movie.springboot.domain.myList.MyList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MyListCommentSaveRequestDto {

    private Long userId;
    private Long movieId;
    private String comment;

    @Builder
    public MyListCommentSaveRequestDto(Long userId, Long movieId, String comment) {
        this.userId = userId;
        this.movieId = movieId;
        this.comment = comment;
    }

    public MyList toEntity() {
        return MyList.builder()
                .userId(userId)
                .movieId(movieId)
                .comment(comment)
                .build();
    }

}
