package com.example.cesai.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "cartoon_table")
@Entity
@Data
public class CartoonEntity extends BaseEntity implements Serializable {
    @Column(name = "cartoon", nullable = false)
    private String cartoonUrl;

    @Column(name = "title", nullable = false, length = 30)
    private String cartoonTitle;

    @Column(name = "picture")
    private String cartoonPictureUrl;

    @Column(name = "collection_id", length = 10)
    private Integer collectionId;

    public String getCartoonUrl() {
        return cartoonUrl;
    }

    public void setCartoonUrl(String cartoonUrl) {
        this.cartoonUrl = cartoonUrl;
    }

    public String getCartoonTitle() {
        return cartoonTitle;
    }

    public void setCartoonTitle(String cartoonTitle) {
        this.cartoonTitle = cartoonTitle;
    }

    public String getCartoonPictureUrl() {
        return cartoonPictureUrl;
    }

    public void setCartoonPictureUrl(String cartoonPictureUrl) {
        this.cartoonPictureUrl = cartoonPictureUrl;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }
}
