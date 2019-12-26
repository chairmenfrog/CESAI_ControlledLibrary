package com.example.cesai.controller.bean;

import com.example.cesai.dao.entity.ManagerEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ManagerBean {

    private Integer managerId;
    private String managerName;
    private String managerPsw;
    private Integer managerLev;
    private Date createTime;
    private Date updateTime;

    public static ManagerEntity of(ManagerBean managerBean) {
        ManagerEntity managerEntity = new ManagerEntity();
        managerEntity.setId(managerBean.getManagerId());
        managerEntity.setManagerName(managerBean.getManagerName());
        managerEntity.setManagerPsw(managerBean.getManagerPsw());
        managerEntity.setManagerLev(managerBean.getManagerLev());
        managerEntity.setCreateTime(managerBean.getCreateTime());
        managerEntity.setUpdateTime(managerBean.getUpdateTime());

        return managerEntity;
    }

    public static ManagerBean of(ManagerEntity managerEntity) {
        ManagerBean managerBean = new ManagerBean();
        managerBean.setManagerId(managerEntity.getId());
        managerBean.setManagerName(managerEntity.getManagerName());
        managerBean.setManagerPsw(managerEntity.getManagerPsw());
        managerBean.setManagerLev(managerEntity.getManagerLev());
        managerBean.setCreateTime(managerEntity.getCreateTime());
        managerBean.setUpdateTime(managerEntity.getUpdateTime());

        return managerBean;
    }

}
