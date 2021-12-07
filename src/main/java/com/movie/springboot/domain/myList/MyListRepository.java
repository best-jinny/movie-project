package com.movie.springboot.domain.myList;

import com.movie.springboot.web.dto.MyListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyListRepository extends JpaRepository<MyList, Long> {

    MyListResponseDto findByUserIdAndMovieId(Long userId, Long movieId);
    List<MyListResponseDto> findByUserId(Long userId);

}
