package main.model;

import java.sql.Timestamp;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 14:42
 */
public class Reply {
    private int reID;
    private int reTopicID;
    private int reUserID;
    private String reContents;
    private Timestamp reTime;

    public int getReID() {
        return reID;
    }

    public void setReID(int reID) {
        this.reID = reID;
    }

    public int getReTopicID() {
        return reTopicID;
    }

    public void setReTopicID(int reTopicID) {
        this.reTopicID = reTopicID;
    }

    public int getReUserID() {
        return reUserID;
    }

    public void setReUserID(int reUserID) {
        this.reUserID = reUserID;
    }

    public String getReContents() {
        return reContents;
    }

    public void setReContents(String reContents) {
        this.reContents = reContents;
    }

    public Timestamp getReTime() {
        return reTime;
    }

    public void setReTime(Timestamp reTime) {
        this.reTime = reTime;
    }
}
