package main.model;

import java.sql.Timestamp;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 14:41
 */
public class Topic {
    private int topicID;
    private int topicSectionID;
    private int topicUserID;
    private int topicReplyCount;
    private String topicTitle;
    private String topicContents;
    private Timestamp topicTime;

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public int getTopicSectionID() {
        return topicSectionID;
    }

    public void setTopicSectionID(int topicSectionID) {
        this.topicSectionID = topicSectionID;
    }

    public int getTopicUserID() {
        return topicUserID;
    }

    public void setTopicUserID(int topicUserID) {
        this.topicUserID = topicUserID;
    }

    public int getTopicReplyCount() {
        return topicReplyCount;
    }

    public void setTopicReplyCount(int topicReplyCount) {
        this.topicReplyCount = topicReplyCount;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicContents() {
        return topicContents;
    }

    public void setTopicContents(String topicContents) {
        this.topicContents = topicContents;
    }

    public Timestamp getTopicTime() {
        return topicTime;
    }

    public void setTopicTime(Timestamp topicTime) {
        this.topicTime = topicTime;
    }
}
