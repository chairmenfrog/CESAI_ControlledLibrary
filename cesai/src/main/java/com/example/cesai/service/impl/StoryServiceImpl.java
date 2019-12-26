package com.example.cesai.service.impl;

import com.example.cesai.controller.bean.StoryBean;
import com.example.cesai.dao.entity.StoryEntity;
import com.example.cesai.dao.repository.StoryRepository;
import com.example.cesai.service.StoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StoryServiceImpl implements StoryService {
    @Autowired
    private StoryRepository storyRepository;

    @Override
    public List<StoryBean> getAll() {
        List<StoryEntity> storyEntities = this.storyRepository.findAll();
        List<StoryBean> storyBeans = storyEntities.stream().map(storyEntity -> StoryBean.of(storyEntity))
                .collect(Collectors.toList());

        log.info("all storys are:{}", storyBeans);

        return storyBeans;
    }

    @Override
    public StoryBean createStory(StoryBean storyBean) {
        StoryEntity storyEntity = StoryBean.of(storyBean);
        storyEntity = this.storyRepository.save(storyEntity);
        StoryBean newStoryBean = StoryBean.of(storyEntity);
        return newStoryBean;
    }

    @Override
    public StoryBean getByStoryId(Integer storyId) {
        Boolean isEntityExist=this.storyRepository.findById(storyId).isPresent();
        StoryEntity storyEntity = null;
        if(isEntityExist==true){
            storyEntity=this.storyRepository.findById(storyId).get();
        }else {
            storyEntity=null;
        }

        if (storyEntity!=null){
            StoryBean storyBean = StoryBean.of(storyEntity);
            return storyBean;
        }else{
            return null;
        }
    }

    @Override
    public StoryBean editStory(StoryBean storyBean) {
        log.info("要修改的story为:{}", storyBean);
        StoryEntity storyEntity = this.storyRepository.findById(storyBean.getStoryId()).get();

        storyEntity.setId(storyBean.getStoryId());
        storyEntity.setStoryTitle(storyBean.getStoryTitle());
        storyEntity.setStoryAuthor(storyBean.getStoryAuthor());
        storyEntity.setStoryPictureUrl(storyBean.getStoryPictureUrl());
        storyEntity.setStoryContent(storyBean.getStoryContent());
        storyEntity.setUpdateTime(new Date());

        this.storyRepository.save(storyEntity);
        StoryBean editBean = StoryBean.of(storyEntity);
        return editBean;
    }

    @Override
    public boolean deleteStory(Integer storyId) {
        this.storyRepository.deleteById(storyId);
        return true;
    }
}
