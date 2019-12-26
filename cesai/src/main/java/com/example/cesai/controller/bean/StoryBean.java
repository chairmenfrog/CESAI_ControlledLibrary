package com.example.cesai.controller.bean;

import com.example.cesai.dao.entity.StoryEntity;
import lombok.Data;

import java.util.Date;

@Data
public class StoryBean {
    private Integer storyId;
    private String storyTitle;
    private String storyContent;
    private String storyPictureUrl;
    private String storyAuthor;
    private Date createTime;
    private Date updateTime;

    public static StoryEntity of(StoryBean storyBean) {
        StoryEntity storyEntity = new StoryEntity();
        storyEntity.setId(storyBean.getStoryId());
        storyEntity.setStoryTitle(storyBean.getStoryTitle());
        storyEntity.setStoryContent(storyBean.getStoryContent());
        storyEntity.setStoryPictureUrl(storyBean.getStoryPictureUrl());
        storyEntity.setStoryAuthor(storyBean.getStoryAuthor());
        storyEntity.setCreateTime(storyBean.getCreateTime());
        storyEntity.setUpdateTime(storyBean.getUpdateTime());

        return storyEntity;
    }

    public static StoryBean of(StoryEntity storyEntity) {
        StoryBean storyBean = new StoryBean();
        storyBean.setStoryId(storyEntity.getId());
        storyBean.setStoryTitle(storyEntity.getStoryTitle());
        storyBean.setStoryContent(storyEntity.getStoryContent());
        storyBean.setStoryPictureUrl(storyEntity.getStoryPictureUrl());
        storyBean.setStoryAuthor(storyEntity.getStoryAuthor());
        storyBean.setCreateTime(storyEntity.getCreateTime());
        storyBean.setUpdateTime(storyEntity.getUpdateTime());

        return storyBean;
    }
}
