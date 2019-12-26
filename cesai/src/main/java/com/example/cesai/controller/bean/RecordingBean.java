package com.example.cesai.controller.bean;

import com.example.cesai.dao.entity.RecordingEntity;
import lombok.Data;

import java.util.Date;

@Data
public class RecordingBean {
    private Integer recordingId;
    private Integer userId;
    private Integer storyId;
    private String recordingUrl;
    private String recordingTitle;
    private Date createTime;
    private Date updateTime;

    public static RecordingEntity of(RecordingBean recordingBean) {
        RecordingEntity recordingEntity = new RecordingEntity();
        recordingEntity.setId(recordingBean.getRecordingId());
        recordingEntity.setUserId(recordingBean.getUserId());
        recordingEntity.setStoryId(recordingBean.getStoryId());
        recordingEntity.setRecordingUrl(recordingBean.getRecordingUrl());
        recordingEntity.setRecordingTitle(recordingBean.getRecordingTitle());
        recordingEntity.setCreateTime(recordingBean.getCreateTime());
        recordingEntity.setUpdateTime(recordingBean.getUpdateTime());

        return recordingEntity;
    }

    public static RecordingBean of(RecordingEntity recordingEntity) {
        RecordingBean recordingBean = new RecordingBean();
        recordingBean.setRecordingId(recordingEntity.getId());
        recordingBean.setUserId(recordingEntity.getUserId());
        recordingBean.setStoryId(recordingEntity.getStoryId());
        recordingBean.setRecordingUrl(recordingEntity.getRecordingUrl());
        recordingBean.setRecordingTitle(recordingEntity.getRecordingTitle());
        recordingBean.setCreateTime(recordingEntity.getCreateTime());
        recordingBean.setUpdateTime(recordingEntity.getUpdateTime());

        return recordingBean;
    }
}

