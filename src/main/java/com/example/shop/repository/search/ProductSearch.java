package com.example.shop.repository.search;

import com.example.shop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductSearch {

    Page<Product> searchAll(String keyword,Pageable pageable);

}
