package com.example.mindyawellnessdraft6;

public class Discussion {
    private String discussionTitle;
    private String discussionDate;

    public Discussion(String discussionTitle, String discussionDate) {
        this.discussionTitle = discussionTitle;
        this.discussionDate = discussionDate;
    }

    public String getDiscussionTitle() {
        return discussionTitle;
    }

    public void setDiscussionTitle(String discussionTitle) {
        this.discussionTitle = discussionTitle;
    }

    public String getDiscussionDate() {
        return discussionDate;
    }

    public void setDiscussionDate(String discussionDate) {
        this.discussionDate = discussionDate;
    }
}
