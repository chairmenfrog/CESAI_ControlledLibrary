package com.example.cesai.controller.bean;

import com.example.cesai.dao.entity.CartoonEntity;
import lombok.Data;

import java.util.Date;

@Data
public class CartoonBean {
    private Integer cartoonId;
    private String cartoonUrl;
    private String cartoonTitle;
    private String cartoonPictureUrl;
    private Integer collectionId;
    private Date createTime;
    private Date updateTime;

    public static CartoonEntity of(CartoonBean cartoonBean) {
        CartoonEntity cartoonEntity = new CartoonEntity();
        cartoonEntity.setId(cartoonBean.getCartoonId());
        cartoonEntity.setCartoonUrl(cartoonBean.getCartoonUrl());
        cartoonEntity.setCartoonTitle(cartoonBean.getCartoonTitle());
        cartoonEntity.setCartoonPictureUrl(cartoonBean.getCartoonPictureUrl());
        cartoonEntity.setCollectionId(cartoonBean.getCollectionId());
        cartoonEntity.setCreateTime(cartoonBean.getCreateTime());
        cartoonEntity.setUpdateTime(cartoonBean.getUpdateTime());

        return cartoonEntity;
    }

    public static CartoonBean of(CartoonEntity cartoonEntity) {
        CartoonBean cartoonBean = new CartoonBean();
        cartoonBean.setCartoonId(cartoonEntity.getId());
        cartoonBean.setCartoonUrl(cartoonEntity.getCartoonUrl());
        cartoonBean.setCartoonTitle(cartoonEntity.getCartoonTitle());
        cartoonBean.setCartoonPictureUrl(cartoonEntity.getCartoonPictureUrl());
        cartoonBean.setCollectionId(cartoonEntity.getCollectionId());
        cartoonBean.setCreateTime(cartoonEntity.getCreateTime());
        cartoonBean.setUpdateTime(cartoonEntity.getUpdateTime());

        return cartoonBean;
    }
}
