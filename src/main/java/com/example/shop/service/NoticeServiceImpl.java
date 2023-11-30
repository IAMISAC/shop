package com.example.shop.service;

import com.example.shop.domain.Notice;
import com.example.shop.dto.NoticeDTO;
import com.example.shop.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService{
    private final NoticeRepository noticeRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<NoticeDTO> readAll() {
        List<Notice> result = noticeRepository.findAll();
        List<NoticeDTO> noticeDTO = result.stream()
                .map(notice -> modelMapper.map(notice, NoticeDTO.class))
                .collect(Collectors.toList());
        return noticeDTO;
    }

    @Override
    public NoticeDTO readOne(Long nNo) {
        Optional<Notice> result = noticeRepository.findById(nNo);
        Notice notice = result.orElseThrow();
        NoticeDTO noticeDTO = modelMapper.map(notice, NoticeDTO.class);
        return noticeDTO;
    }

    @Override
    public Long registerNotice(NoticeDTO noticeDTO) {
        Notice notice = modelMapper.map(noticeDTO, Notice.class);
        Long nNo = noticeRepository.save(notice).getNNo();
        return nNo;
    }

    @Override
    public void modifyNotice(NoticeDTO noticeDTO) {
        Optional<Notice> result = noticeRepository.findById(noticeDTO.getNNo());
        Notice notice= result.orElseThrow();
        notice.change(noticeDTO.getNTitle(), noticeDTO.getNContents());
        noticeRepository.save(notice);

    }

    @Override
    public void deleteNotice(Long nNo) {
        noticeRepository.deleteById(nNo);
    }

}
