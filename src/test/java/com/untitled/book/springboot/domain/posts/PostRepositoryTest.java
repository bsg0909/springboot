package com.untitled.book.springboot.domain.posts;
/*
save와 findAll기능을 테스트
 */
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After //junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정, 배포 전 전체 테스트 수행할 때 테스트간 데이터 침범을 막기 위해 사용, 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 h2에 데이터가 그대로 남아 테스트 실패의 원인이 됨
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder() //테이블 posts에 insert/update 쿼리를 실행(id값 있으면 update, 없으면 insert)
        .title(title)
        .content(content)
        .author("bsg0909@gmail.com")
        .build());

        //when
        List<Posts> postsList = postsRepository.findAll(); //테이블 posts에 있는 모든 데이터를 조회

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2020,11,21,0,0,0);
        postsRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>> CreateDate ="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
