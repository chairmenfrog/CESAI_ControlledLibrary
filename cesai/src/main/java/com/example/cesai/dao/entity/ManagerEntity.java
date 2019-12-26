package com.example.cesai.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Table(name = "manager_table")
@Entity
@Data
public class ManagerEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 2053229982530588531L;

    @Column(name = "manager_name", nullable = false, length = 30)
    private String managerName;

    @Column(name = "password", nullable = false)
    private String managerPsw;

    @Column(name = "level", nullable = false, length = 2)
    private Integer managerLev;


    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPsw() {
        return managerPsw;
    }

    public void setManagerPsw(String managerPsw) {
        this.managerPsw = managerPsw;
    }

    public Integer getManagerLev() {
        return managerLev;
    }

    public void setManagerLev(Integer managerLev) {
        this.managerLev = managerLev;
    }
}
