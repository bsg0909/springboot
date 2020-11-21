package com.untitled.book.springboot.domain.posts;

import com.untitled.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter //클래스 내 모든 필드의 Getter 메소드를 자동 추가
@NoArgsConstructor //기본 생성자 자동 추가 = public Posts(){}
@Entity //테이블과 링크될 클래스임을 나타냄, 기본값으로 클래스의 카멜케이스 이름을 언더스코어 테이블명으로 가진다.
public class Posts extends BaseTimeEntity {
    @Id //해당 테이블의 PK필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성규칙
    /*
    스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 된다.
    버전 차이에 대한 설명은 https://jojoldu.tistory.com/295
     */
    private long id;

    @Column(length = 500, nullable = false) //테이블의 컬럼(선언하지 않아도 해당 클래스 필드는 컬럼이 됨)->기본값 외 추가옵션이 필요할 때 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)//문자열의 경우 기본값이 255인데 500으로 변경하거나, 타입을 text로 변경하고 싶을 때 등
    private String content;

    private String author;

    @Builder //
    public Posts(String title, String content, String author) { //해당 클래스 빌더 클래스 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌드에 포함
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
