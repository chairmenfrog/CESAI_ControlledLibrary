package com.example.cesai.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "user_table")
@Entity
@Data
public class UserEntity extends BaseEntity implements Serializable {
    @Column(name = "user_name", nullable = false, length = 30)
    private String userName;

    @Column(name = "password", nullable = false)
    private String userPsw;

    @Column(name = "tel", length = 11)
    private Long userTel;

    @Column(name = "mail", length = 50)
    private String userMail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    public Long getUserTel() {
        return userTel;
    }

    public void setUserTel(Long userTel) {
        this.userTel = userTel;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }
}
