package com.example.shop.service;

import com.example.shop.domain.Member;
import com.example.shop.domain.MemberRole;
import com.example.shop.dto.MemberJoinDTO;
import com.example.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberJoinDTO findMemberByUsername(String username) {
        // 데이터베이스에서 username에 해당하는 사용자 정보를 검색하는 로직 구현
        // 예시로, 여기서는 빈 MemberJoinDTO 객체를 반환합니다.
        return new MemberJoinDTO();
    }

    @Override
    public void join(MemberJoinDTO memberJoinDTO)throws MidExistException{
        String mid = memberJoinDTO.getMid();
        log.info(mid);
        boolean exist = memberRepository.existsById(mid);
        if(exist){
            throw new MidExistException();
        }
        log.info("possible id...");
        Member member = modelMapper.map(memberJoinDTO, Member.class);
        member.addRole(MemberRole.USER);
        log.info("Role good...");
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        log.info("encode good...");
        memberRepository.save(member);
    }

    @Override
    public void delete(MemberJoinDTO memberJoinDTO){
        memberRepository.deleteById(memberJoinDTO.getMid());
    }

    @Override
    public MemberJoinDTO readOneId(String mid){
        Optional<Member> memberOptional = memberRepository.findById(mid);
        Member member = memberOptional.orElseThrow();
        return modelMapper.map(member, MemberJoinDTO.class);
    }

    @Override
    public void updateMemberInfo(MemberJoinDTO memberJoinDTO)throws EntityNotFoundException {
        String mid = memberJoinDTO.getMid();
        Member member = memberRepository.findById(mid).orElseThrow(() -> new EntityNotFoundException("Member not found with ID: " + mid));

        member.updateInfo(
                passwordEncoder.encode(memberJoinDTO.getMpw()),
                memberJoinDTO.getMname(),
                memberJoinDTO.getMaddress(),
                memberJoinDTO.getMphoneNumber()
        );

        memberRepository.save(member);
    }

    public boolean existsById(String mid) {
        return memberRepository.existsByMid(mid);
    }




}
