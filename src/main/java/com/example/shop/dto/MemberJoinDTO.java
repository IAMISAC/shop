package com.example.shop.dto;

import lombok.Data;
import lombok.Setter;

@Data
public class MemberJoinDTO {
    private String mid;
    private String mpw;
    private String mname;
    private String maddress;
    private String mphoneNumber;
}