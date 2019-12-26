package com.example.cesai.service.impl;

import com.example.cesai.controller.bean.CartoonBean;
import com.example.cesai.dao.entity.CartoonEntity;
import com.example.cesai.dao.repository.CartoonRepository;
import com.example.cesai.service.CartoonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartoonServiceImpl implements CartoonService {
    @Autowired
    private CartoonRepository cartoonRepository;

    @Override
    public List<CartoonBean> getAll() {
        List<CartoonEntity> cartoonEntities = this.cartoonRepository.findAll();
        List<CartoonBean> cartoonBeans = cartoonEntities.stream().map(cartoonEntity -> CartoonBean.of(cartoonEntity))
                .collect(Collectors.toList());

        log.info("all cartoons are:{}", cartoonBeans);

        return cartoonBeans;
    }

    @Override
    public CartoonBean createCartoon(CartoonBean cartoonBean) {
        CartoonEntity cartoonEntity = CartoonBean.of(cartoonBean);
        cartoonEntity = this.cartoonRepository.save(cartoonEntity);
        CartoonBean newCartoonBean = CartoonBean.of(cartoonEntity);
        return newCartoonBean;
    }

    @Override
    public CartoonBean editCartoon(CartoonBean cartoonBean) {
        log.info("要修改的cartoon为:{}", cartoonBean);
        CartoonEntity cartoonEntity = this.cartoonRepository.findById(cartoonBean.getCartoonId()).get();

        cartoonEntity.setId(cartoonBean.getCartoonId());
        cartoonEntity.setCartoonTitle(cartoonBean.getCartoonTitle());
        cartoonEntity.setCollectionId(cartoonBean.getCollectionId());
        cartoonEntity.setCartoonPictureUrl(cartoonBean.getCartoonPictureUrl());
        cartoonEntity.setCartoonUrl(cartoonBean.getCartoonUrl());
        cartoonEntity.setUpdateTime(new Date());

        this.cartoonRepository.save(cartoonEntity);
        CartoonBean editBean = CartoonBean.of(cartoonEntity);
        return editBean;
    }

    @Override
    public CartoonBean getByCartoonId(Integer cartoonId) {
        CartoonEntity cartoonEntity=this.cartoonRepository.findById(cartoonId).get();

        CartoonBean cartoonBean=CartoonBean.of(cartoonEntity);

        return cartoonBean;
    }

    @Override
    public boolean deleteCartoon(Integer cartoonId) {
        this.cartoonRepository.deleteById(cartoonId);
        return true;
    }

}
