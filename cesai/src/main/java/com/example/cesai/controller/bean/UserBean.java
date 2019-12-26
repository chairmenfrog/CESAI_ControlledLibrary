package com.example.cesai.controller.bean;

import com.example.cesai.dao.entity.UserEntity;
import lombok.Data;

import java.util.Date;

@Data
public class UserBean {
    private Integer userId;
    private String userName;
    private String userPsw;
    private Long userTel;
    private String userMail;
    private Date createTime;
    private Date updateTime;

    public static UserEntity of(UserBean userBean) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userBean.getUserId());
        userEntity.setUserName(userBean.getUserName());
        userEntity.setUserPsw(userBean.getUserPsw());
        userEntity.setUserTel(userBean.getUserTel());
        userEntity.setUserMail(userBean.getUserMail());
        userEntity.setCreateTime(userBean.getCreateTime());
        userEntity.setUpdateTime(userBean.getUpdateTime());

        return userEntity;
    }

    public static UserBean of(UserEntity userEntity) {
        UserBean userBean = new UserBean();
        userBean.setUserId(userEntity.getId());
        userBean.setUserName(userEntity.getUserName());
        userBean.setUserPsw(userEntity.getUserPsw());
        userBean.setUserTel(userEntity.getUserTel());
        userBean.setUserMail(userEntity.getUserMail());
        userBean.setCreateTime(userEntity.getCreateTime());
        userBean.setUpdateTime(userEntity.getUpdateTime());

        return userBean;
    }
}
