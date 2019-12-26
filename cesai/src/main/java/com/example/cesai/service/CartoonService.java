package com.example.cesai.service;

import com.example.cesai.controller.bean.CartoonBean;

import java.util.List;

public interface CartoonService {

    public List<CartoonBean> getAll();

    public CartoonBean createCartoon(CartoonBean cartoonBean);

    public CartoonBean editCartoon(CartoonBean cartoonBean);

    public CartoonBean getByCartoonId(Integer cartoonId);

    public boolean deleteCartoon(Integer cartoonId);
}
