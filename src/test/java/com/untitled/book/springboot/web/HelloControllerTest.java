package com.untitled.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //junit 내장된 실행자 외의 실행자를 실행 -> 스프링 부트 테스트와 junit의 연결자
/* 일반 테스트 코드
@WebMvcTest(controllers = HelloController.class) //web에 집중할 수 있는 어노테이션

    //사용가능 : @Controller, @ControllerAdvice 등
    //사용불가능 : @Service, @Component, @repository 등

public class HelloControllerTest {
    @Autowired //스프링이 관리하는 Bean을 주입
    private MockMvc mvc; //웹 API를 테스트 할 때 -> 스프링 Mvc 테스트의 시작점

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMvc를 통해 /hello주소로 HTTP GET 요청(체이닝 지원되어 하단 검증기능을 이어서 선언가능)
            .andExpect(status().isOk()) //mvc.perform의 결과를 검증, HTTP Header의 Status를 검증(200인지 아닌지)
            .andExpect(content().string(hello)); //mvc.perform의 결과를 검증, 응답 본문의 내용을 검증(컨트롤러에서 리턴한 "hello" 값)
    }
}
*/
//롬복을 사용한 테스트 코드
@WebMvcTest
public class HelloControllerTest{
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String userFirst = "hello";
        int userNum = 1000;
        mvc.perform(get("/hello/dto")
                                .param("userFirst", userFirst) //API테스트에 사용될 요청 파라미터 설정(단 String만 가능)
                                .param("userNum", String.valueOf(userNum)
        ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userFirst", is(userFirst)))
                //jsonPath는 JSON 응답값을 필드별로 검증할 수 있는 메소드
                //$.을 기준으로 필드명을 명시
                .andExpect(jsonPath("$.userNum", is(userNum)));
    }
}