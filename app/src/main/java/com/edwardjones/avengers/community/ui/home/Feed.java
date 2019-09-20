package com.edwardjones.avengers.community.ui.home;

import java.time.Instant;

public class Feed {
    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    Integer feedId;
    String author;
    String content;
    Instant timestamp;
    String picturePath;

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
