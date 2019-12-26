package com.example.cesai.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "collection_table")
@Entity
@Data
public class CollectionEntity extends BaseEntity implements Serializable {
    @Column(name = "title", nullable = false, length = 30)
    private String collectionTitle;

    @Column(name = "picture")
    private String collectionPictureUrl;

    public String getCollectionTitle() {
        return collectionTitle;
    }

    public void setCollectionTitle(String collectionTitle) {
        this.collectionTitle = collectionTitle;
    }

    public String getCollectionPictureUrl() {
        return collectionPictureUrl;
    }

    public void setCollectionPictureUrl(String collectionPictureUrl) {
        this.collectionPictureUrl = collectionPictureUrl;
    }
}
