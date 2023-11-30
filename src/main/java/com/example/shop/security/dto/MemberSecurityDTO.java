package com.example.shop.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User{
    private String mid;
    private String mpw;
    private String mname;
    private String maddress;
    private String mphoneNumber;

    public MemberSecurityDTO(String username, String password, String mname,
                             String maddress, String mphoneNumber,
                             Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);
        this.mid = username;
        this.mpw = password;
        this.mname = mname;
        this.maddress = maddress;
        this.mphoneNumber = mphoneNumber;

    }
}
