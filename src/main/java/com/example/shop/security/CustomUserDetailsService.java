package com.example.shop.security;

import com.example.shop.domain.Member;
import com.example.shop.repository.MemberRepository;
import com.example.shop.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

    private final MemberRepository memberRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        log.info("loadUserByUsername : " + username);

        Optional<Member> result = memberRepository.getWithRoles(username);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("username not found");
        }
        Member member = result.get();
        MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
                member.getMid(), member.getMpw(), member.getMname(),
                member.getMaddress(), member.getMphoneNumber(),
                member.getRoleSet().stream().map(memberRole ->
                        new SimpleGrantedAuthority("ROLE_"+memberRole.name())).collect(Collectors.toList())
        );

        return memberSecurityDTO;
    }
}
