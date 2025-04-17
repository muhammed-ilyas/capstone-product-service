package com.aim.productservice.repositories;

public class CustomQuery {
    public static final String GET_PRODUCT_BY_CATEGORY_NAME =
            "select * from product where category_id in (select id from category where name = :categoryName)";
}
