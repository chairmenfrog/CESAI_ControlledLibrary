package com.example.cesai.dao.repository;

import com.example.cesai.dao.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerEntity, Integer> {

    public ManagerEntity findByManagerNameAndManagerPsw(String managerName, String managerPsw);

    public ManagerEntity findByManagerName(String managerName);
}
