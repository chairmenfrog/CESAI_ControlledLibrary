package com.example.cesai.service.impl;

import com.example.cesai.controller.bean.CollectionBean;
import com.example.cesai.dao.entity.CollectionEntity;
import com.example.cesai.dao.repository.CollectionRepository;
import com.example.cesai.service.CollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;

    @Override
    public List<CollectionBean> getAll() {
        List<CollectionEntity> collectionEntities = this.collectionRepository.findAll();
        List<CollectionBean> collectionBeans = collectionEntities.stream().map(collectionEntity -> CollectionBean.of(collectionEntity))
                .collect(Collectors.toList());

        log.info("all collections are:{}", collectionBeans);

        return collectionBeans;
    }

    @Override
    public CollectionBean createCollection(CollectionBean collectionBean) {
        CollectionEntity collectionEntity = CollectionBean.of(collectionBean);
        collectionEntity = this.collectionRepository.save(collectionEntity);
        CollectionBean newCollectionBean = CollectionBean.of(collectionEntity);
        return newCollectionBean;
    }

    @Override
    public CollectionBean editCollection(CollectionBean collectionBean) {
        log.info("要修改的collection为:{}", collectionBean);
        CollectionEntity collectionEntity = this.collectionRepository.findById(collectionBean.getCollectionId()).get();

        collectionEntity.setId(collectionBean.getCollectionId());
        collectionEntity.setCollectionTitle(collectionBean.getCollectionTitle());
        collectionEntity.setCollectionPictureUrl(collectionBean.getCollectionPictureUrl());
        collectionEntity.setUpdateTime(new Date());

        this.collectionRepository.save(collectionEntity);
        CollectionBean editBean = CollectionBean.of(collectionEntity);
        return editBean;
    }

    @Override
    public CollectionBean getByCollectionId(Integer collectionId) {
        Boolean isEntityExist=this.collectionRepository.findById(collectionId).isPresent();
        CollectionEntity collectionEntity=null;
        if(isEntityExist==true){
            collectionEntity=this.collectionRepository.findById(collectionId).get();
        }else {
            collectionEntity=null;
        }

        if(collectionEntity!=null){
            CollectionBean collectionBean=CollectionBean.of(collectionEntity);
            return collectionBean;
        }else {
            return null;
        }
    }

    @Override
    public boolean deleteCollection(Integer collectionId) {
        this.collectionRepository.deleteById(collectionId);
        return true;
    }
}
