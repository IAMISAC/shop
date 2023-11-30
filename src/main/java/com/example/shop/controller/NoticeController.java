package com.example.shop.controller;

import com.example.shop.dto.NoticeDTO;
import com.example.shop.dto.PageRequestDTO;
import com.example.shop.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/notice")
@Log4j2
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        List<NoticeDTO> noticeDTO = noticeService.readAll();
        model.addAttribute("dto", noticeDTO);
    }

    @PostMapping("/register")
    public String register(@Valid NoticeDTO noticeDTO, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            log.info("Error Occurred");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/notice/register";
        }
        Long nNo = noticeService.registerNotice(noticeDTO);
        redirectAttributes.addFlashAttribute("result", nNo);
        return "redirect:/notice/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long nNo, PageRequestDTO pageRequestDTO, Model model){
        NoticeDTO noticeDTO = noticeService.readOne(nNo);
        model.addAttribute("dto", noticeDTO);
    }

    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid NoticeDTO noticeDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("nNo", noticeDTO.getNNo());
            return "redirect:/notice/modify?"+link;
        }
        noticeService.modifyNotice(noticeDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("nNo", noticeDTO.getNNo());
        return "redirect:/notice/read";
    }

    @PostMapping("/delete")
    public String delete(NoticeDTO noticeDTO, RedirectAttributes redirectAttributes){
        long nNo = noticeDTO.getNNo();
        noticeService.deleteNotice(nNo);

        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:notice/list";
    }
}
