package com.movie.springboot.service.myList;


import com.movie.springboot.domain.myList.MyList;
import com.movie.springboot.domain.myList.MyListRepository;
import com.movie.springboot.web.dto.MyListResponseDto;
import com.movie.springboot.web.dto.MyListSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MyListService {
    private final MyListRepository myListRepository;

    @Transactional
    public Long save(MyListSaveRequestDto requestDto) {

        System.out.println("@@@@@@myList requestDto@@@@@@@@" + requestDto);
        System.out.println("@@@@@@myList - requestDto user_id@@@@@@@@" + requestDto.getUserId());
        System.out.println("@@@@@@myList - requestDto movie_id@@@@@@@@" + requestDto.getMovieId());


        // 중복 체크
        MyList myList = myListRepository.findByUserIdAndMovieId(requestDto.getUserId(), requestDto.getMovieId());


        if(myList == null) {
            return myListRepository.save(requestDto.toEntity()).getId();
        } else {
            return 1L;
        }
    }

    @Transactional
    public MyList findByUserIdAndMovieId(Long userId, Long movieId) {
        return myListRepository.findByUserIdAndMovieId(userId, movieId);
    }

    @Transactional
    public List<MyListResponseDto> findByUserId(Long userId) {
        return myListRepository.findByUserId(userId);
    }

    @Transactional
    public void delete(Long id) {
        MyList myList = myListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 항목이 없습니다. id = " + id));
        myListRepository.delete(myList);
    }
}
