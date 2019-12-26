package com.example.cesai.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "recording_table")
@Entity
@Data
public class RecordingEntity extends BaseEntity implements Serializable {
    @Column(name = "user_id", nullable = false, length = 10)
    private Integer userId;

    @Column(name = "story_id", nullable = false, length = 10)
    private Integer storyId;

    @Column(name = "recording", nullable = false)
    private String recordingUrl;

    @Column(name = "title", nullable = false, length = 30)
    private String recordingTitle;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public String getRecordingUrl() {
        return recordingUrl;
    }

    public void setRecordingUrl(String recordingUrl) {
        this.recordingUrl = recordingUrl;
    }

    public String getRecordingTitle() {
        return recordingTitle;
    }

    public void setRecordingTitle(String recordingTitle) {
        this.recordingTitle = recordingTitle;
    }
}
