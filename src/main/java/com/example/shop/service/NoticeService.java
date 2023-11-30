package com.example.shop.service;

import com.example.shop.domain.Notice;
import com.example.shop.dto.NoticeDTO;

import java.util.List;

public interface NoticeService {
    List<NoticeDTO> readAll();

    NoticeDTO readOne(Long nNo);

    Long registerNotice(NoticeDTO noticeDTO);

    void modifyNotice(NoticeDTO noticeDTO);

    void deleteNotice(Long nNo);

}
