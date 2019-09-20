package com.edwardjones.avengers.community.ui.home;

import java.time.Instant;

public class Feed {
    String author;
    String content;
    Instant timestamp;
    String picture;

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getPicture() {
        return picture;
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

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
