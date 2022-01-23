package com.skripsi.howtotrade.model;

import java.util.Date;

public class Comment {
    private int commentId;
    private String description;
    private Date createdDate;
    private Topic topicId;
    private int userId;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCommentId() {
        return this.commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Topic get() {
		return this.topicId;
	}

    public void set(Topic topicId ) {
		this.topicId = topicId;
	}

    

}
