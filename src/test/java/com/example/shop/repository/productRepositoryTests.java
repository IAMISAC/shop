package com.example.shop.repository;

import com.example.shop.domain.Product;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class productRepositoryTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testProductRegister(){
        IntStream.rangeClosed(1, 20).forEach(i -> {
            Product product = Product.builder()
                    .productId((long) i)
                    .productName("product"+i)
                    .productPrice(BigDecimal.valueOf(i))
                    .pType("pType"+i)
                    .build();
            Product result = productRepository.save(product);
        });
    }

    @Test
    public void testReadOne(){
        Optional<Product> result = productRepository.findById(4L);
        Product product = result.orElseThrow();
    }
}
