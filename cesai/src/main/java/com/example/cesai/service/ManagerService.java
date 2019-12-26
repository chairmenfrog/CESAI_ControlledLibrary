package com.example.cesai.service;

import com.example.cesai.controller.bean.ManagerBean;

import java.util.List;

public interface ManagerService {

    public List<ManagerBean> getAll();

    public Integer login(String managerName, String managerPsw);

    public ManagerBean createManager(ManagerBean managerBean);

    public ManagerBean getByManagerName(String managerName);

    public ManagerBean editManager(ManagerBean managerBean);

    public ManagerBean getByManagerId(Integer managerId);

    public boolean deleteManager(Integer managerId);
}
