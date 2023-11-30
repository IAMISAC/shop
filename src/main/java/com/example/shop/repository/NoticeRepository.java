package com.example.shop.repository;

import com.example.shop.domain.Notice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @EntityGraph(attributePaths = {"?"})
    @Query("select n from Notice n where n.nNo=:nNo")
    Optional<Notice> findByNo(Long nNo);

}
