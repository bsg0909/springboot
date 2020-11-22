package com.untitled.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email); //email을 통해 기존유저인지 아닌지 판단하기 위한 메소드
}
