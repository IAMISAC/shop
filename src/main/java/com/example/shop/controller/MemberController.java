package com.example.shop.controller;


import com.example.shop.dto.MemberJoinDTO;
import com.example.shop.repository.MemberRepository;
import com.example.shop.security.dto.MemberSecurityDTO;
import com.example.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public void loginGET(String error, String logout){
        log.info("login get...");
        log.info("logout : "+ logout);

        if(logout != null){
            log.info("user logout...");
        }
    }

    @GetMapping("/join")
    public void joinGET() { log.info("join get..."); }

    @PostMapping("/join")
    public String joinPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes){
        log.info("join post...");

        try{
            memberService.join(memberJoinDTO);
        }catch (MemberService.MidExistException e){
            redirectAttributes.addFlashAttribute("error", "mid");
            return "redirect:/member/join";
        }

        redirectAttributes.addFlashAttribute("result", "success");
        return "redirect:/member/login";
    }

    @GetMapping("/checkId")
    @ResponseBody
    public Map<String, Boolean> checkId(@RequestParam String mid) throws Exception {
        boolean isAvailable = !memberRepository.existsByMid(mid);
        log.info(isAvailable);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isAvailable", isAvailable);
        return response;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/myPage")
    public void myPageGET(HttpSession session, Model model) throws Exception {
        log.info("join get...");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof MemberSecurityDTO) {
            MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
            String mid = memberSecurityDTO.getMid(); // 현재 로그인한 사용자의 아이디
            MemberJoinDTO memberInfo = memberService.readOneId(mid);

            model.addAttribute("memberinfo", memberInfo);
            session.setAttribute("loginInfo", memberInfo);
            log.info("session...");
            log.info(session.getAttribute("loginInfo"));
        }
    }

    @GetMapping("/findId")
    public void findIdGET() { log.info("findId get..."); }

    @GetMapping("/findPassword")
    public void findPasswordGET() { log.info("findPassword get..."); }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/memberInfoDelete")
    public String memberInfoDelete(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes) throws Exception {
        log.info("delete..."+memberJoinDTO.getMid());
        memberService.delete(memberJoinDTO);
        redirectAttributes.addFlashAttribute("result", "deleted");
        return "redirect:/main";
    }

    @PostMapping("/memberInfoUpdate")
    public String memberInfoUpdate(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes) throws Exception {

        log.info("memberInfo Update...");
        memberService.updateMemberInfo(memberJoinDTO);
        redirectAttributes.addFlashAttribute("result", "updated");
        redirectAttributes.addAttribute("mid", memberJoinDTO.getMid());
        return "redirect:/myPage";
    }


}
