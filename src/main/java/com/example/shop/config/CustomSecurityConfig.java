package com.example.shop.config;


import com.example.shop.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@Log4j2
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

    private final DataSource dataSource;
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        log.info("----web Config---"); // 로그 기록
        // 정적 리소스에 대한 보안 검사 무시
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl(); // JDBC 기반 토큰 저장소 구현
        repo.setDataSource(dataSource); // 데이터 소스 설정
        return repo; // 저장소 반환
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // 비밀번호 암호화를 위한 Bcrypt 암호화기 반환
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        log.info("-------configure------"); // 로그 기록

        // SavedRequestAwareAuthenticationSuccessHandler를 생성하고 기본 리디렉션 주소 설정
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/shopping/main");

        // 폼 로그인 설정, 로그인 페이지 및 로그인 성공시 리디렉션 주소 설정
        http.formLogin().loginPage("/member/login")
                .successHandler(successHandler);
        http.csrf().disable(); // CSRF 보호 기능 비활성화

        // "remember me" 기능 설정, 키, 토큰 저장소, 사용자 서비스 및 토큰 유효성 기간 설정
        http.rememberMe().key("12345678").tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(60*60*24*30);
        return http.build(); // 보안 필터 체인 구성 반환
    }
}

