package com.example.shop.repository.search;

import com.example.shop.domain.Product;
import com.example.shop.domain.QProduct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {

    public ProductSearchImpl(){super(Product.class);}

    @Override
    public Page<Product> searchAll(String keyword, Pageable pageable){
        QProduct product = QProduct.product;
        JPQLQuery<Product> query = from(product);

        if(keyword != null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(product.productName.contains(keyword));

            query.where(booleanBuilder);
        }
        query.where(product.productId.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);
        List<Product> list = query.fetch();
        long count = query.fetchCount();
        return new PageImpl<>(list, pageable, count);
    }




}
