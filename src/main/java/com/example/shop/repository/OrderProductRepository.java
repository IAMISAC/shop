package com.example.shop.repository;

import com.example.shop.domain.OrderProduct;
import com.example.shop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Collectors;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    @Query(value = "SELECT p.* " +
            "FROM order_product op " +
            "JOIN product p ON op.product_id = p.product_id " +
            "GROUP BY op.product_id " +
            "ORDER BY SUM(op.quantity) DESC " +
            "LIMIT 8", nativeQuery = true)
    List<Product> findTop8Products();



}
