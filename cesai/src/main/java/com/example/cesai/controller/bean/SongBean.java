package com.example.cesai.controller.bean;

import com.example.cesai.dao.entity.SongEntity;
import lombok.Data;

import java.util.Date;

@Data
public class SongBean {
    private Integer songId;
    private String songUrl;
    private String songTitle;
    private String songPictureUrl;
    private String songSinger;
    private Date createTime;
    private Date updateTime;

    public static SongEntity of(SongBean songBean) {
        SongEntity songEntity = new SongEntity();
        songEntity.setId(songBean.getSongId());
        songEntity.setSongUrl(songBean.getSongUrl());
        songEntity.setSongTitle(songBean.getSongTitle());
        songEntity.setSongPictureUrl(songBean.getSongPictureUrl());
        songEntity.setSongSinger(songBean.getSongSinger());
        songEntity.setCreateTime(songBean.getCreateTime());
        songEntity.setUpdateTime(songBean.getUpdateTime());

        return songEntity;
    }

    public static SongBean of(SongEntity songEntity) {
        SongBean songBean = new SongBean();
        songBean.setSongId(songEntity.getId());
        songBean.setSongUrl(songEntity.getSongUrl());
        songBean.setSongTitle(songEntity.getSongTitle());
        songBean.setSongPictureUrl(songEntity.getSongPictureUrl());
        songBean.setSongSinger(songEntity.getSongSinger());
        songBean.setCreateTime(songEntity.getCreateTime());
        songBean.setUpdateTime(songEntity.getUpdateTime());

        return songBean;
    }
}
