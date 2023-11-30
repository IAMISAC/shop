package com.example.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private int quantity;
    private String pType;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private List<String> productImg;

}
