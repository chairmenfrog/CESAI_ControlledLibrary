package com.example.cesai.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "draw_table")
@Entity
@Data
public class DrawingEntity extends BaseEntity implements Serializable {
    @Column(name = "user_id", nullable = false, length = 10)
    private Integer userId;

    @Column(name = "image", nullable = false)
    private String drawingImageUrl;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDrawingImageUrl() {
        return drawingImageUrl;
    }

    public void setDrawingImageUrl(String drawingImageUrl) {
        this.drawingImageUrl = drawingImageUrl;
    }
}
