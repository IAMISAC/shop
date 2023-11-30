package com.example.shop.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wishlist_item")
@ToString(exclude = "wid")
public class WishlistItem extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wid;

    @ManyToOne
    @JoinColumn(name = "member_id") // 외래키
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id") // 외래키
    private Product product;

    public void setMember(Member member) {
        this.member = member;
    }
}
