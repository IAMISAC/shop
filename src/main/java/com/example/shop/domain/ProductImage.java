package com.example.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Value("D:\\project\\productimg")
    private String imagePath; // 이미지 파일 경로

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; // 해당 이미지가 속한 상품

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
