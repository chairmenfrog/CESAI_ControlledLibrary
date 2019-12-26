package com.example.cesai.service;

import com.example.cesai.controller.bean.UserBean;

import java.util.List;

public interface UserService {

    public List<UserBean> getAll();

    public UserBean createUser(UserBean userBean);

    public UserBean editUser(UserBean userBean);

    public UserBean getByUserId(Integer userId);

    public UserBean getByUserName(String userName);

    public boolean deleteUser(Integer userId);
}
