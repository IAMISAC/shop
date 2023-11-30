package com.example.shop.domain;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Long, Integer> cartItems = new HashMap<>();

    public void addCartItem(Long productId, int quantity) {
        // 이미 해당 제품이 장바구니에 있는 경우 수량을 더함
        cartItems.merge(productId, quantity, Integer::sum);
    }

    public void removeCartItem(Long productId) {
        // 해당 제품을 장바구니에서 제거
        cartItems.remove(productId);
    }

    public Map<Long, Integer> getCartItems() {
        return cartItems;
    }
}