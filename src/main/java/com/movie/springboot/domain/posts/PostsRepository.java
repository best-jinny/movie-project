package com.movie.springboot.domain.posts;

// Post 클래스로 Database 접근하게 해 줄 JpaRepository - 기본 CRUD 메소드 자동 생성
// Entity클래스와 기본 Entity Repository는 함께 위치해야함 - 도메인 패키지에서 함께 관리

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
