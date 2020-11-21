package com.untitled.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
/*
myBatis DAO = Jpa Repository
인터페이스 -> JpaRepository<Entity 클래스, PK타입>형식으로 작성 -> CRUD 메소드 자동 생성
Entity클래스와 기본 레포지토리는 항상 함께 있어야 함에 주의!
 */
public interface PostsRepository extends JpaRepository<Posts, Long>{

}
