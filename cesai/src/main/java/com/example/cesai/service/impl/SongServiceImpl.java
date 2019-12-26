package com.example.cesai.service.impl;

import com.example.cesai.controller.bean.SongBean;
import com.example.cesai.dao.entity.SongEntity;
import com.example.cesai.dao.repository.SongRepository;
import com.example.cesai.service.SongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepository songRepository;

    @Override
    public List<SongBean> getAll() {
        List<SongEntity> songEntities = this.songRepository.findAll();
        List<SongBean> songBeans = songEntities.stream().map(songEntity -> SongBean.of(songEntity))
                .collect(Collectors.toList());

        log.info("all songs are:{}", songBeans);

        return songBeans;
    }

    @Override
    public SongBean createSong(SongBean songBean) {
        SongEntity songEntity = SongBean.of(songBean);
        songEntity = this.songRepository.save(songEntity);
        SongBean newSongBean = SongBean.of(songEntity);
        return newSongBean;
    }

    @Override
    public SongBean editSong(SongBean songBean) {
        log.info("要修改的song为:{}", songBean);
        SongEntity songEntity = this.songRepository.findById(songBean.getSongId()).get();

        songEntity.setId(songBean.getSongId());
        songEntity.setSongTitle(songBean.getSongTitle());
        songEntity.setSongSinger(songBean.getSongSinger());
        songEntity.setSongPictureUrl(songBean.getSongPictureUrl());
        songEntity.setSongUrl(songBean.getSongUrl());
        songEntity.setUpdateTime(new Date());

        this.songRepository.save(songEntity);
        SongBean editBean = SongBean.of(songEntity);
        return editBean;
    }

    @Override
    public SongBean getBySongId(Integer songId) {
        SongEntity songEntity=this.songRepository.findById(songId).get();

        SongBean songBean=SongBean.of(songEntity);

        return songBean;
    }

    @Override
    public boolean deleteSong(Integer songId) {
        this.songRepository.deleteById(songId);
        return true;
    }
}
