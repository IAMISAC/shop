package com.example.shop.controller;

import com.example.shop.dto.PageRequestDTO;
import com.example.shop.dto.QnaDTO;
import com.example.shop.service.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/qna")
@Log4j2
@RequiredArgsConstructor
public class QnaController {
    private final QnaService qnaService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        List<QnaDTO> qnaDTO = qnaService.readAll();
        model.addAttribute("dto", qnaDTO);
    }

    @PostMapping("/register")
    public String register(@Valid QnaDTO qnaDTO, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            log.info("Error Occurred");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/qna/register";
        }
        Long qNo = qnaService.registerQna(qnaDTO);
        redirectAttributes.addFlashAttribute("result", qNo);
        return "redirect:/qna/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long qNo, PageRequestDTO pageRequestDTO, Model model){
        QnaDTO qnaDTO = qnaService.readOne(qNo);
        model.addAttribute("dto", qnaDTO);
    }

    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid QnaDTO qnaDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("qNo", qnaDTO.getQNo());
            return "redirect:/qna/modify?"+link;
        }
        qnaService.modifyQna(qnaDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("qNo", qnaDTO.getQNo());
        return "redirect:/qna/read";
    }

    @PostMapping("/delete")
    public String delete(QnaDTO qnaDTO, RedirectAttributes redirectAttributes){
        long qNo = qnaDTO.getQNo();
        qnaService.deleteQna(qNo);

        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:qna/list";
    }
}
