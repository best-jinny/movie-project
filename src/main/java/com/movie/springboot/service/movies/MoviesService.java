package com.movie.springboot.service.movies;

import com.movie.springboot.api.MovieApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MoviesService {
    private final MovieApiClient movieApiClient;

    @Transactional(readOnly = true)
    public Object findByKeyword(String keyword) {
        return movieApiClient.requestMovie(keyword);
    }
}