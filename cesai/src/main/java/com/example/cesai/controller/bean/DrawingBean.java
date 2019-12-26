package com.example.cesai.controller.bean;

import com.example.cesai.dao.entity.DrawingEntity;
import lombok.Data;

import java.util.Date;

@Data
public class DrawingBean {
    private Integer drawingId;
    private Integer userId;
    private String drawingImageUrl;
    private Date createTime;
    private Date updateTime;

    public static DrawingEntity of(DrawingBean drawingBean) {
        DrawingEntity drawingEntity = new DrawingEntity();
        drawingEntity.setId(drawingBean.getDrawingId());
        drawingEntity.setUserId(drawingBean.getUserId());
        drawingEntity.setDrawingImageUrl(drawingBean.getDrawingImageUrl());
        drawingEntity.setCreateTime(drawingBean.getCreateTime());
        drawingEntity.setUpdateTime(drawingBean.getUpdateTime());

        return drawingEntity;
    }

    public static DrawingBean of(DrawingEntity drawingEntity) {
        DrawingBean drawingBean = new DrawingBean();
        drawingBean.setDrawingId(drawingEntity.getId());
        drawingBean.setUserId(drawingEntity.getUserId());
        drawingBean.setDrawingImageUrl(drawingEntity.getDrawingImageUrl());
        drawingBean.setCreateTime(drawingEntity.getCreateTime());
        drawingBean.setUpdateTime(drawingEntity.getUpdateTime());

        return drawingBean;
    }
}
