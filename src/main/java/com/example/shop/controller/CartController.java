package com.example.shop.controller;

import com.example.shop.domain.ShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping("/add")
    public String addToCart(HttpServletRequest request, Long productId, int quantity) {
        // 세션을 가져오거나 없으면 새로 생성
        HttpSession session = request.getSession(true);

        // 세션에 저장된 장바구니를 가져오거나 없으면 새로 생성
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        // 장바구니에 제품 추가
        cart.addCartItem(productId, quantity);

        return "redirect:/product/list"; // 장바구니에 제품을 추가한 후 상품 목록 페이지로 리다이렉트
    }

    @GetMapping
    public void viewCart(HttpServletRequest request) {
        // 세션을 가져오거나 없으면 새로 생성
        HttpSession session = request.getSession(true);

        // 세션에 저장된 장바구니를 가져오거나 없으면 새로 생성
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        // 장바구니 정보를 출력하거나 전달할 뷰 페이지로 이동
        // (여기서는 간단하게 출력만 예시로 표시)
        System.out.println("Cart Items: " + cart.getCartItems());
    }
}
