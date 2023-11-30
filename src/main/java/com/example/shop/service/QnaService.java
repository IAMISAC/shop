package com.example.shop.service;

import com.example.shop.dto.QnaDTO;

import java.util.List;

public interface QnaService {
    List<QnaDTO> readAll();

    QnaDTO readOne(Long qNo);

    Long registerQna(QnaDTO qnaDTO);

    void modifyQna(QnaDTO qnaDTO);

    void deleteQna(Long qNo);
}
