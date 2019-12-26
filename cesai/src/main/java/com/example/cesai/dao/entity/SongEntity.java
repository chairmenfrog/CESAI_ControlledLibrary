package com.example.cesai.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "song_table")
@Entity
@Data
public class SongEntity extends BaseEntity implements Serializable {
    @Column(name = "song", nullable = false)
    private String songUrl;

    @Column(name = "title", nullable = false, length = 30)
    private String songTitle;

    @Column(name = "picture")
    private String songPictureUrl;

    @Column(name = "singer", nullable = false, length = 30)
    private String songSinger;

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongPictureUrl() {
        return songPictureUrl;
    }

    public void setSongPictureUrl(String songPictureUrl) {
        this.songPictureUrl = songPictureUrl;
    }

    public String getSongSinger() {
        return songSinger;
    }

    public void setSongSinger(String songSinger) {
        this.songSinger = songSinger;
    }
}
