package com.example.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QnaDTO {
    private Long qNo;
    private String qTitle;
    private String qContents;
    private String qWriter;
    private LocalDateTime regDate, modDate;
}
