package com.example.shop.service;

import com.example.shop.domain.Notice;
import com.example.shop.domain.Qna;
import com.example.shop.dto.NoticeDTO;
import com.example.shop.dto.QnaDTO;
import com.example.shop.repository.QnaRepository;
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
public class QnaServiceImpl implements QnaService{
    private final QnaRepository qnaRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<QnaDTO> readAll() {
        List<Qna> result = qnaRepository.findAll();
        List<QnaDTO> qnaDTO = result.stream()
                .map(qna -> modelMapper.map(qna, QnaDTO.class))
                .collect(Collectors.toList());
        return qnaDTO;
    }

    @Override
    public QnaDTO readOne(Long qNo) {
        Optional<Qna> result = qnaRepository.findById(qNo);
        Qna qna = result.orElseThrow();
        QnaDTO qnaDTO = modelMapper.map(qna, QnaDTO.class);
        return qnaDTO;
    }

    @Override
    public Long registerQna(QnaDTO qnaDTO) {
        Qna qna = modelMapper.map(qnaDTO, Qna.class);
        Long qNo = qnaRepository.save(qna).getQNo();
        return qNo;
    }

    @Override
    public void modifyQna(QnaDTO qnaDTO) {
        Optional<Qna> result = qnaRepository.findById(qnaDTO.getQNo());
        Qna qna= result.orElseThrow();
        qna.change(qnaDTO.getQTitle(), qnaDTO.getQContents());
        qnaRepository.save(qna);

    }

    @Override
    public void deleteQna(Long qNo) {
        qnaRepository.deleteById(qNo);
    }

}
