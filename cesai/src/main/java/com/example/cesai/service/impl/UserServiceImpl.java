package com.example.cesai.service.impl;

import com.example.cesai.controller.bean.UserBean;
import com.example.cesai.dao.entity.UserEntity;
import com.example.cesai.dao.repository.UserRepository;
import com.example.cesai.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserBean> getAll() {
        List<UserEntity> userEntities = this.userRepository.findAll();
        List<UserBean> userBeans = userEntities.stream().map(userEntity -> UserBean.of(userEntity))
                .collect(Collectors.toList());

        log.info("all users are:{}", userBeans);

        return userBeans;
    }

    @Override
    public UserBean createUser(UserBean userBean) {
        UserEntity userEntity = UserBean.of(userBean);
        userEntity = this.userRepository.save(userEntity);
        UserBean newUserBean = UserBean.of(userEntity);
        return newUserBean;
    }

    @Override
    public UserBean editUser(UserBean userBean) {
        log.info("要修改的user为:{}", userBean);
        UserEntity userEntity = this.userRepository.findById(userBean.getUserId()).get();

        userEntity.setId(userBean.getUserId());
        userEntity.setUserName(userBean.getUserName());
        userEntity.setUserMail(userBean.getUserMail());
        userEntity.setUserTel(userBean.getUserTel());
        userEntity.setUserPsw(userBean.getUserPsw());
        userEntity.setUpdateTime(new Date());

        this.userRepository.save(userEntity);
        UserBean editBean = UserBean.of(userEntity);
        return editBean;
    }

    @Override
    public UserBean getByUserId(Integer userId) {
        Boolean isEntityExist=this.userRepository.findById(userId).isPresent();
        UserEntity userEntity=null;
        if(isEntityExist==true){
            userEntity=this.userRepository.findById(userId).get();
        }else {
            userEntity=null;
        }

        if(userEntity!=null){
            UserBean userBean=UserBean.of(userEntity);
            return userBean;
        }else {
            return null;
        }
    }

    @Override
    public UserBean getByUserName(String userName) {
        UserEntity userEntity = this.userRepository.findByUserName(userName);
        if (userEntity!=null){
            UserBean userBean = UserBean.of(userEntity);
            return userBean;
        }else {
            return null;
        }
    }

    @Override
    public boolean deleteUser(Integer userId) {
        this.userRepository.deleteById(userId);
        return true;
    }
}
