package com.movie.springboot.service.movies;

import com.movie.springboot.api.MovieApiClient;
import com.movie.springboot.domain.movies.Movies;
import com.movie.springboot.domain.movies.MoviesRepository;
import com.movie.springboot.web.dto.MoviesListResponseDto;
import com.movie.springboot.web.dto.MoviesResponseDto;
import com.movie.springboot.web.dto.MoviesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MoviesService {
    private final MovieApiClient movieApiClient;
    private final MoviesRepository moviesRepository;


    @Transactional(readOnly = true)
    public Object findByKeyword(String keyword) {
        return movieApiClient.requestMovie(keyword);
    }

    @Transactional
    public Long save(MoviesSaveRequestDto requestDto) {

        // 중복 체크
        Long id = moviesRepository.findByLink(requestDto.getLink());

        System.out.println("@@@@@@@@movie_id@@@@@@@@@@: " + id);

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
        return new MoviesListResponseDto();
    }

    public List<MoviesListResponseDto> makeList(List<Long> movieIds) {


       return

    }

}