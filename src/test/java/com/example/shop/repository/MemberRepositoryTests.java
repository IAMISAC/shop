package com.example.shop.repository;

import com.example.shop.domain.Member;
import com.example.shop.domain.MemberRole;
import com.example.shop.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testMemberJoin(){
        IntStream.rangeClosed(1,10).forEach(i-> {
            Member member = Member.builder()
                    .mid("memm"+i)
                    .mpw(passwordEncoder.encode("1111"))
                    .mname("isak"+i)
                    .maddress("house"+1)
                    .mphoneNumber("010"+i+"1234567")
                    .build();
            member.addRole(MemberRole.USER);
            if(i>8){
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        });
    }
}
