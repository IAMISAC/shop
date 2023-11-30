package com.example.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Member extends BaseEntity{
    @Id
    private String mid;

    private String mpw;

    private String mname;

    private String maddress;

    private String mphoneNumber;

    public void updateInfo(String mpw, String mname, String maddress, String mphoneNumber) {
        this.mpw = mpw;
        this.mname = mname;
        this.maddress = maddress;
        this.mphoneNumber = mphoneNumber;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<WishlistItem> wishlist = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void changePassword(String mpw){
        this.mpw = mpw;
    }

    public void changeAddress(String maddress){
        this.maddress = maddress;
    }

    public void changePhoneNumber(String mphoneNumber){
        this.mphoneNumber = mphoneNumber;
    }

    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }

    public void clearRole(){
        this.roleSet.clear();
    }

    // WishlistItem 추가 메소드
    public void addWishlistItem(WishlistItem wishlistItem) {
        this.wishlist.add(wishlistItem);
        wishlistItem.setMember(this);
    }

    // WishlistItem 삭제 메소드
    public void removeWishlistItem(WishlistItem wishlistItem) {
        this.wishlist.remove(wishlistItem);
        wishlistItem.setMember(null);
    }
}
