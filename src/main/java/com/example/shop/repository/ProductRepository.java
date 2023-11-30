package com.example.shop.repository;

import com.example.shop.domain.Product;
import com.example.shop.repository.search.ProductSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> , ProductSearch {

    @EntityGraph(attributePaths = {"productImages"})
    @Query("select b from Product b where b.productId=:productId")
    Optional<Product> findByIdWithImages(Long productId);

    @EntityGraph(attributePaths = {"productImages"})
    @Query("select b from Product b order by  b.productId")
    Page<Product> findWithImages(Pageable pageable);

    @EntityGraph(attributePaths = {"productImages"})
    @Query("SELECT p FROM Product p WHERE p.pType = :pType")
    Page<Product> findTypeAll(String pType, Pageable pageable);;
}