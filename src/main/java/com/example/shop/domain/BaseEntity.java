package com.example.shop.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass// 공통적인 동작이 일어나는 경우 한 번에 처리하도록 하는 것
@EntityListeners(value={AuditingEntityListener.class})
// auditing entity listener - entity에서 어떠한 동작이 발생하면 자동으로 읽어옴 (listener - 이벤트가 생기는지 듣고 있는 것)
@Getter
abstract class BaseEntity { // 추상 클래스
    @CreatedDate // dao를 없이 DB에 '날짜를 생성'하여 넣어주는 annotation
    @Column(name="regdate", updatable = false) // DB 테이블 컬럼 // DB의 regdate라는 이름의 컬럼과 연결 // updatable - 수정 가능 여부
    private LocalDateTime regDate;

    @LastModifiedDate // 수정된 날짜를 DB로 불러옴
    @Column(name="moddate")
    private LocalDateTime modDate;
}
