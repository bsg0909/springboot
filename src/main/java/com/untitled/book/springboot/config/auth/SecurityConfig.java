package com.untitled.book.springboot.config.auth;

import com.untitled.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final CustomOauth2UserService customOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console화면을 사용하기 위해 해당 옵션들을 disable합니다.
        .and()
                .authorizeRequests() // url별 권한 관리를 설정하는 옵션의 시작점, 이게 먼저 선언되야 antMatchers사용 가능
                .antMatchers("/", "/css/**", "/images/**","/js/**", "h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //권한 관리 대상을 지정하는 옵션, url,Http 메소드별 관리 가능, "/"등 지정된 url들은 permitAll옵션을 통해 전체 열람 권한을 주었다. "/api/v1/**"주소를 가진 api는 user권한을 가진 사람만 가능하도록 함
                .anyRequest().authenticated() //설정된 값들 이외 나머지 url, authenticated()를 추가하여 모두 인증된 사용자들에게만 허용
        .and()
                .logout()
                    .logoutSuccessUrl("/") //로그아웃 기능에 대한 설정 진입점, 성공 시 /주소로 이동
        .and()
                .oauth2Login() //OAuth2 로그인 기능에 대한 설정 진입점
                    .userInfoEndpoint() //OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정
                        .userService(customOauth2UserService);
                        //소셜 로그인 성공 시 후속 조치를 진행할 UserService 인텊이스의 구현체를 등록
                        //리소스 서버(소셜미디어)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시 가능
    }
}
