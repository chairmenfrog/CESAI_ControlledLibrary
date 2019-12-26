package com.example.cesai.service;

import com.example.cesai.controller.bean.CollectionBean;

import java.util.List;

public interface CollectionService {

    public List<CollectionBean> getAll();

    public CollectionBean createCollection(CollectionBean collectionBean);

    public CollectionBean editCollection(CollectionBean collectionBean);

    public CollectionBean getByCollectionId(Integer collectionId);

    public boolean deleteCollection(Integer collectionId);
}
