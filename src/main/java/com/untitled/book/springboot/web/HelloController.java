package com.untitled.book.springboot.web;

import com.untitled.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //JSON을 반환하는 컨트롤러로 변환
public class HelloController {
    @GetMapping("/hello/dto") //HTTP Method의 get요청을 받는 API
    public HelloResponseDto helloDto(@RequestParam("userFirst") String userFirst, //외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
                                    @RequestParam("userNum") int userNum) {
        return new HelloResponseDto(userFirst, userNum);
    } 
    
}
