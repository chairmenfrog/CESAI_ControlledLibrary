package com.example.cesai.service;

import com.example.cesai.controller.bean.StoryBean;

import java.util.List;

public interface StoryService {

    public List<StoryBean> getAll();

    public StoryBean createStory(StoryBean storyBean);

    public StoryBean getByStoryId(Integer storyId);

    public StoryBean editStory(StoryBean storyBean);

    public boolean deleteStory(Integer storyId);
}
