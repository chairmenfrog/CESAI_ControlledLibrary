package com.example.cesai.service;

import com.example.cesai.controller.bean.SongBean;

import java.util.List;

public interface SongService {

    public List<SongBean> getAll();

    public SongBean createSong(SongBean songBean);

    public SongBean editSong(SongBean songBean);

    public SongBean getBySongId(Integer songId);

    public boolean deleteSong(Integer songId);
}
