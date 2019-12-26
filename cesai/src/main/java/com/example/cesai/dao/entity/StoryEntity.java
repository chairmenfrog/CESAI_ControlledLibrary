package com.example.cesai.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "story_table")
@Entity
@Data
public class StoryEntity extends BaseEntity implements Serializable {
    @Column(name = "title", nullable = false, length = 30)
    private String storyTitle;

    @Column(name = "text", nullable = false)
    private String storyContent;

    @Column(name = "picture")
    private String storyPictureUrl;

    @Column(name = "author", nullable = false, length = 30)
    private String storyAuthor;

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getStoryContent() {
        return storyContent;
    }

    public void setStoryContent(String storyContent) {
        this.storyContent = storyContent;
    }

    public String getStoryPictureUrl() {
        return storyPictureUrl;
    }

    public void setStoryPictureUrl(String storyPictureUrl) {
        this.storyPictureUrl = storyPictureUrl;
    }

    public String getStoryAuthor() {
        return storyAuthor;
    }

    public void setStoryAuthor(String storyAuthor) {
        this.storyAuthor = storyAuthor;
    }
}
