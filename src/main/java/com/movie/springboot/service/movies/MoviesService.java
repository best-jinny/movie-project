package com.movie.springboot.service.movies;

import com.movie.springboot.api.MovieApiClient;
import com.movie.springboot.domain.movies.Movies;
import com.movie.springboot.domain.movies.MoviesRepository;
import com.movie.springboot.domain.myList.MyList;
import com.movie.springboot.service.myList.MyListService;
import com.movie.springboot.web.dto.MoviesListResponseDto;
import com.movie.springboot.web.dto.MoviesResponseDto;
import com.movie.springboot.web.dto.MoviesSaveRequestDto;
import com.movie.springboot.web.dto.MyListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MoviesService {
    private final MovieApiClient movieApiClient;
    private final MoviesRepository moviesRepository;
    private final MyListService myListService;


    @Transactional(readOnly = true)
    public Object findByKeyword(String keyword) throws IOException {
        return movieApiClient.requestMovie(keyword);
    }

    @Transactional
    public Long save(MoviesSaveRequestDto requestDto) {

        // 중복 체크
        Long id = moviesRepository.findByLink(requestDto.getLink());

        if(id == null) {
            return moviesRepository.save(requestDto.toEntity()).getId();
        } else {
            return id;
        }
    }

    @Transactional
    public Long findByLink(String link) {
        return moviesRepository.findByLink(link);
    }

    @Transactional
    public MoviesListResponseDto findById(Long id) {
        Movies entity = moviesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 영화가 없습니다."));
        return new MoviesListResponseDto(entity);
    }

    @Transactional
    public List<MoviesListResponseDto> makeList(Long userId) {

        // myList 에서 userId로 찾은 영화 아이디들
        List<MyListResponseDto> movieIds = myListService.findByUserId(userId);

        // 영화 아이디를 통해서 영화 정보 조회
        List<MoviesListResponseDto> movies = new ArrayList<>();

        for(int i = 0 ; i < movieIds.size(); i++) {
            movies.add(findById(movieIds.get(i).getMovieId()));
        }

       return  movies;

    }

}