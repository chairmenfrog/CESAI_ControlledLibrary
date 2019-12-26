package com.example.cesai.controller.bean;

import com.example.cesai.dao.entity.CollectionEntity;
import lombok.Data;

import java.util.Date;

@Data
public class CollectionBean {
    private Integer collectionId;
    private String collectionTitle;
    private String collectionPictureUrl;
    private Date createTime;
    private Date updateTime;

    public static CollectionEntity of(CollectionBean collectionBean) {
        CollectionEntity collectionEntity = new CollectionEntity();
        collectionEntity.setId(collectionBean.getCollectionId());
        collectionEntity.setCollectionTitle(collectionBean.getCollectionTitle());
        collectionEntity.setCollectionPictureUrl(collectionBean.getCollectionPictureUrl());
        collectionEntity.setCreateTime(collectionBean.getCreateTime());
        collectionEntity.setUpdateTime(collectionBean.getUpdateTime());

        return collectionEntity;
    }

    public static CollectionBean of(CollectionEntity collectionEntity) {
        CollectionBean collectionBean = new CollectionBean();
        collectionBean.setCollectionId(collectionEntity.getId());
        collectionBean.setCollectionTitle(collectionEntity.getCollectionTitle());
        collectionBean.setCollectionPictureUrl(collectionEntity.getCollectionPictureUrl());
        collectionBean.setCreateTime(collectionEntity.getCreateTime());
        collectionBean.setUpdateTime(collectionEntity.getUpdateTime());

        return collectionBean;
    }
}
