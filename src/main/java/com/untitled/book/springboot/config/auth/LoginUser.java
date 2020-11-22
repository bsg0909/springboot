package com.untitled.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //어노테이션이 생성될 위치를 지정, ElementType.PARAMETER->메소드의 파라미터로 선언된 객체를 지정
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { //어노테이션 클래스로 지정(LoginUser 라는 이름을 가진 어노테이션 생성)
}
