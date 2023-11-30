package com.example.shop.controller;


import com.example.shop.dto.PageRequestDTO;
import com.example.shop.dto.PageResponseDTO;
import com.example.shop.dto.ProductDTO;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shopping")
@Log4j2
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public String getProductPage(@PathVariable Long productId, Model model) {
        ProductDTO productDTO = productService.readOne(productId);
        model.addAttribute("product", productDTO);
        return "shopping/product"; // 이는 templates 디렉토리에 있는 product.html 파일의 이름과 일치해야 합니다.
    }


    @GetMapping("/shop")
    public void getAllProducts(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<ProductDTO> productDTOList = productService.readWithImageAll(pageRequestDTO);
        model.addAttribute("responseDTO", productDTOList);
    }

    @GetMapping("/type/{pType}")
    public String getProductsByType(@PathVariable String pType, PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<ProductDTO> productDTOList = productService.readTypeAll(pType, pageRequestDTO);

        model.addAttribute("pageRequestDTO", pageRequestDTO);
        model.addAttribute("responseDTO", productDTOList);

        return "productList"; // 해당 뷰의 이름
    }

    @GetMapping("/main")
    public void getTop8(PageRequestDTO pageRequestDTO, Model model) {
        List<ProductDTO> productDTOList = productService.readTop8();

        model.addAttribute("pageRequestDTO", pageRequestDTO);
        model.addAttribute("productDTOList", productDTOList);

    }
}
