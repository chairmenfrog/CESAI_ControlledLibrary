package com.example.cesai.service.impl;

import com.example.cesai.controller.bean.RecordingBean;
import com.example.cesai.dao.entity.RecordingEntity;
import com.example.cesai.dao.repository.RecordingRepository;
import com.example.cesai.service.RecordingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RecordingServiceImpl implements RecordingService {
    @Autowired
    private RecordingRepository recordingRepository;

    @Override
    public List<RecordingBean> getAll() {
        List<RecordingEntity> recordingEntities = this.recordingRepository.findAll();
        List<RecordingBean> recordingBeans = recordingEntities.stream().map(recordingEntity -> RecordingBean.of(recordingEntity))
                .collect(Collectors.toList());

        log.info("all recordings are:{}", recordingBeans);

        return recordingBeans;
    }

    @Override
    public RecordingBean createRecording(RecordingBean recordingBean) {
        RecordingEntity recordingEntity = RecordingBean.of(recordingBean);
        recordingEntity = this.recordingRepository.save(recordingEntity);
        RecordingBean newRecordingBean = RecordingBean.of(recordingEntity);
        return newRecordingBean;
    }

    @Override
    public RecordingBean editRecording(RecordingBean recordingBean) {
        log.info("要修改的recording为:{}", recordingBean);
        RecordingEntity recordingEntity = this.recordingRepository.findById(recordingBean.getRecordingId()).get();

        recordingEntity.setId(recordingBean.getRecordingId());
        recordingEntity.setRecordingTitle(recordingBean.getRecordingTitle());
        recordingEntity.setRecordingUrl(recordingBean.getRecordingUrl());
        recordingEntity.setStoryId(recordingBean.getStoryId());
        recordingEntity.setUserId(recordingBean.getUserId());
        recordingEntity.setUpdateTime(new Date());

        this.recordingRepository.save(recordingEntity);
        RecordingBean editBean = RecordingBean.of(recordingEntity);
        return recordingBean;
    }

    @Override
    public RecordingBean getByRecordingId(Integer recordingId) {
        RecordingEntity recordingEntity=this.recordingRepository.findById(recordingId).get();

        RecordingBean recordingBean=RecordingBean.of(recordingEntity);

        return recordingBean;
    }

    @Override
    public boolean deleteRecording(Integer recordingId) {
        this.recordingRepository.deleteById(recordingId);
        return true;
    }
}
