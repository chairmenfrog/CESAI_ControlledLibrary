package com.example.cesai.service.impl;

import com.example.cesai.controller.bean.ManagerBean;
import com.example.cesai.dao.entity.ManagerEntity;
import com.example.cesai.dao.repository.ManagerRepository;
import com.example.cesai.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public List<ManagerBean> getAll() {
        List<ManagerEntity> managerEntities = this.managerRepository.findAll();
        List<ManagerBean> managerBeans = managerEntities.stream().map(managerEntity -> ManagerBean.of(managerEntity))
                .collect(Collectors.toList());

        log.info("all managers are:{}", managerBeans);

        return managerBeans;
    }

    @Override
    public Integer login(String managerName, String managerPsw) {
        ManagerEntity managerEntity = managerRepository.findByManagerName(managerName);
        log.info("查找到的用户为:{}",managerEntity);
        String mPsw = null;
        log.info("mPsw1为:{}",mPsw);
        if (managerEntity!=null){
            mPsw = managerEntity.getManagerPsw();
            log.info("mPsw2为:{}",mPsw);
        }

        if (managerEntity == null) {//用户不存在
            return 2002;
        } else if (!mPsw.equals(managerPsw)) {//用户输入密码错误
            return 2003;
        } else {//登录成功
            return 1001;
        }
    }

    @Override
    public ManagerBean createManager(ManagerBean managerBean) {
        ManagerEntity managerEntity = ManagerBean.of(managerBean);
        managerEntity = this.managerRepository.save(managerEntity);
        ManagerBean newManagerBean = ManagerBean.of(managerEntity);
        return newManagerBean;
    }

    @Override
    public ManagerBean getByManagerName(String managerName) {
        ManagerEntity managerEntity = this.managerRepository.findByManagerName(managerName);
        if (managerEntity!=null){
            ManagerBean managerBean = ManagerBean.of(managerEntity);
            return managerBean;
        }else {
            return null;
        }
    }

    @Override
    public ManagerBean editManager(ManagerBean managerBean) {
        log.info("serviceImpl中要被修改为manager:{}", managerBean);
        ManagerEntity managerEntity = this.managerRepository.findById(managerBean.getManagerId()).get();

        managerEntity.setId(managerBean.getManagerId());
        managerEntity.setManagerName(managerBean.getManagerName());
        managerEntity.setManagerLev(managerBean.getManagerLev());
        managerEntity.setManagerPsw(managerBean.getManagerPsw());
        managerEntity.setUpdateTime(new Date());

        this.managerRepository.save(managerEntity);
        ManagerBean editBean = ManagerBean.of(managerEntity);
        return editBean;
    }

    @Override
    public ManagerBean getByManagerId(Integer managerId) {
        ManagerEntity managerEntity = this.managerRepository.findById(managerId).get();

        ManagerBean managerBean = ManagerBean.of(managerEntity);

        return managerBean;
    }

    @Override
    public boolean deleteManager(Integer managerId) {
        this.managerRepository.deleteById(managerId);
        return true;
    }


}
