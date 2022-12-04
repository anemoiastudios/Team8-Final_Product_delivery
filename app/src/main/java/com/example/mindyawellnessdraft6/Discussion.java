package com.example.mindyawellnessdraft6;

public class Discussion {
    private String discussionTitle;
    private String discussionDate;
    private String discussionDescription;
    private String discussionProviderUid;
    private String discussionId;

    public Discussion(String discussionTitle, String discussionDate, String discussionDescription, String discussionProviderUid, String discussionId) {
        this.discussionTitle = discussionTitle;
        this.discussionDate = discussionDate;
        this.discussionDescription = discussionDescription;
        this.discussionProviderUid = discussionProviderUid;
        this.discussionId = discussionId;
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

    public String getDiscussionProviderUid() {
        return discussionProviderUid;
    }

    public void setDiscussionProviderUid(String discussionProviderUid) {
        this.discussionProviderUid = discussionProviderUid;
    }

    public String getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(String discussionId) {
        this.discussionId = discussionId;
    }

    public String getDiscussionDescription() {
        return discussionDescription;
    }

    public void setDiscussionDescription(String discussionDescription) {
        this.discussionDescription = discussionDescription;
    }
}
