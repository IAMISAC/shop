package com.example.shop.service;

import com.example.shop.domain.Product;
import com.example.shop.dto.PageRequestDTO;
import com.example.shop.dto.PageResponseDTO;
import com.example.shop.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    void productRegister(ProductDTO productDTO);

    ProductDTO readOne (Long productid);

    PageResponseDTO<ProductDTO> readAll(PageRequestDTO pageRequestDTO);
    PageResponseDTO<ProductDTO> readWithImageAll(PageRequestDTO pageRequestDTO);

    ProductDTO readOnewithImage(long productId);

    PageResponseDTO<ProductDTO> readTypeAll(String pType, PageRequestDTO pageRequestDTO);

    List<ProductDTO> readTop8();

    public class ProductNotFoundException extends RuntimeException {

        public ProductNotFoundException(String message) {
            super(message);
        }
    }
}