package com.example.shop.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notice extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nNo;
    private String nTitle;
    private String nContents;
    private String nName = "관리자";

    public void change(String nTitle, String nContents){
        this.nTitle = nTitle;
        this.nContents = nContents;
    }

}
