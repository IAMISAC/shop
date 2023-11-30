package com.example.shop.repository;

import com.example.shop.domain.Notice;
import com.example.shop.domain.Qna;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QnaRepository extends JpaRepository <Qna,Long> {
    @EntityGraph(attributePaths = {"?"})
    @Query("select q from Qna q where q.qNo=:qNo")
    Optional<Qna> findByNo(Long qNo);
}
