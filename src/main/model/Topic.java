package main.model;

import java.sql.Timestamp;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 14:41
 */
public class Topic {
    private int tID;
    private int tSectionID;
    private int tUserID;
    private int tReplyCount;
    private String tTitle;
    private String tContents;
    private Timestamp tTime;

    public int gettID() {
        return tID;
    }

    public void settID(int tID) {
        this.tID = tID;
    }

    public int gettSectionID() {
        return tSectionID;
    }

    public void settSectionID(int tSectionID) {
        this.tSectionID = tSectionID;
    }

    public int gettUserID() {
        return tUserID;
    }

    public void settUserID(int tUserID) {
        this.tUserID = tUserID;
    }

    public int gettReplyCount() {
        return tReplyCount;
    }

    public void settReplyCount(int tReplyCoun) {
        this.tReplyCount = tReplyCoun;
    }

    public String gettTitle() {
        return tTitle;
    }

    public void settTitle(String tTitle) {
        this.tTitle = tTitle;
    }

    public String gettContents() {
        return tContents;
    }

    public void settContents(String tContents) {
        this.tContents = tContents;
    }

    public Timestamp gettTime() {
        return tTime;
    }

    public void settTime(Timestamp tTime) {
        this.tTime = tTime;
    }
}
