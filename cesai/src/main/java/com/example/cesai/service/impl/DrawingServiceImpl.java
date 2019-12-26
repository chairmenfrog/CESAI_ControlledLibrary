package com.example.cesai.service.impl;

import com.example.cesai.controller.bean.DrawingBean;
import com.example.cesai.dao.entity.DrawingEntity;
import com.example.cesai.dao.repository.DrawingRepository;
import com.example.cesai.service.DrawingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DrawingServiceImpl implements DrawingService {
    @Autowired
    private DrawingRepository drawingRepository;

    @Override
    public List<DrawingBean> getAll() {
        List<DrawingEntity> drawingEntities = this.drawingRepository.findAll();
        List<DrawingBean> drawingBeans = drawingEntities.stream().map(drawingEntity -> DrawingBean.of(drawingEntity))
                .collect(Collectors.toList());

        log.info("all drawings are:{}", drawingBeans);

        return drawingBeans;
    }

    @Override
    public DrawingBean createDrawing(DrawingBean drawingBean) {
        DrawingEntity drawingEntity = DrawingBean.of(drawingBean);
        drawingEntity = this.drawingRepository.save(drawingEntity);
        DrawingBean newDrawingBean = DrawingBean.of(drawingEntity);
        return newDrawingBean;
    }

    @Override
    public DrawingBean editDrawing(DrawingBean drawingBean) {
        log.info("要修改的drawing为:{}", drawingBean);
        DrawingEntity drawingEntity = this.drawingRepository.findById(drawingBean.getDrawingId()).get();

        drawingEntity.setId(drawingBean.getDrawingId());
        drawingEntity.setDrawingImageUrl(drawingBean.getDrawingImageUrl());
        drawingEntity.setUserId(drawingBean.getUserId());
        drawingEntity.setUpdateTime(new Date());

        this.drawingRepository.save(drawingEntity);
        DrawingBean editBean = DrawingBean.of(drawingEntity);
        return editBean;
    }

    @Override
    public DrawingBean getByDrawingId(Integer drawingId) {
        DrawingEntity drawingEntity=this.drawingRepository.findById(drawingId).get();

        DrawingBean drawingBean=DrawingBean.of(drawingEntity);

        return drawingBean;
    }

    @Override
    public boolean deleteDrawing(Integer drawingId) {
        this.drawingRepository.deleteById(drawingId);
        return true;
    }
}
