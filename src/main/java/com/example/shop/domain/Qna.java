package com.example.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Qna extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long qNo;
    private String qTitle;
    private String qContents;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    private String qWriter;

    public void change(String qTitle, String qContents){
        this.qTitle = qTitle;
        this.qContents = qContents;
    }

}
