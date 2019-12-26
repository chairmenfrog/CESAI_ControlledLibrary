package com.example.cesai.service;

import com.example.cesai.controller.bean.RecordingBean;

import java.util.List;

public interface RecordingService {

    public List<RecordingBean> getAll();

    public RecordingBean createRecording(RecordingBean recordingBean);

    public RecordingBean editRecording(RecordingBean recordingBean);

    public RecordingBean getByRecordingId(Integer recordingId);

    public boolean deleteRecording(Integer recordingId);
}
