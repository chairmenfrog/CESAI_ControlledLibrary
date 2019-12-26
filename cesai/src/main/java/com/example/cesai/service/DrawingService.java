package com.example.cesai.service;

import com.example.cesai.controller.bean.DrawingBean;

import java.util.List;

public interface DrawingService {

    public List<DrawingBean> getAll();

    public DrawingBean createDrawing(DrawingBean drawingBean);

    public DrawingBean editDrawing(DrawingBean drawingBean);

    public DrawingBean getByDrawingId(Integer drawingId);

    public boolean deleteDrawing(Integer drawingId);
}
