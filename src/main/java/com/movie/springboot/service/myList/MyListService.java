package com.movie.springboot.service.myList;


import com.movie.springboot.domain.myList.MyList;
import com.movie.springboot.domain.myList.MyListRepository;
import com.movie.springboot.web.dto.MyListSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MyListService {
    private final MyListRepository myListRepository;

    @Transactional
    public Long save(MyListSaveRequestDto requestDto) {

        // 중복 체크
        Optional<MyList> myList = myListRepository.findByUserIdAndMovieId(requestDto.getUserId(), requestDto.getMovieId());

        if(myList == null) {
            return myListRepository.save(requestDto.toEntity()).getId();
        } else {
            return 1L;
        }
    }

    @Transactional
    public Optional<MyList> findByUserIdAndMovieId(Long userId, Long movieId) {
        return myListRepository.findByUserIdAndMovieId(userId, movieId);
    }
}
