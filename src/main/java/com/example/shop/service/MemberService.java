package com.example.shop.service;

import com.example.shop.dto.MemberJoinDTO;
import com.example.shop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface MemberService {

    static class MidExistException extends Exception{

    }


    void join(MemberJoinDTO memberJoinDTO)throws MidExistException;

    boolean existsById(String mid)throws Exception;

    void updateMemberInfo(MemberJoinDTO memberJoinDTO)throws Exception;

    void delete(MemberJoinDTO memberJoinDTO)throws Exception;

    MemberJoinDTO readOneId(String mid)throws Exception;



}
