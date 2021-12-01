package com.movie.springboot.domain.myList;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyListRepository extends JpaRepository<MyList, Long> {

    Optional<MyList> findByUserIdAndMovieId(Long userId, Long movieId);

}
