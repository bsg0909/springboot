package com.untitled.book.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트() {
        //given
        String userFirst = "test";
        int userNum = 1000;
        
        //when
        HelloResponseDto dto = new HelloResponseDto(userFirst, userNum);
        
        //then
        assertThat(dto.getUserFirst()).isEqualTo(userFirst);
        assertThat(dto.getUserNum()).isEqualTo(userNum);
        /*
        assertThat은 assertj라는 테스트 검증 라이브러리의 검증 메소드로 검증 대상을 메소드 인자로 받으며, 체이닝 지원
        
        isEqualTo는 assertj의 동등 ㅂㅣ교 메소드, assertThat에 있는 값과 isEqualTo의 값을 비교하여 같을 때만 성공

        assertj의 장점 -> junit쪽은 CoreMatchehers 라이브러리가 필요, 자동완성 지원이 잘 됨
         */
    }
}
