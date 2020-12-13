package main.model;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 13:02
 */

import java.util.Date;

public class User {
    public enum Sex{
        male,
        female
    }

    private int uID;
    private String userName;
    private String userPassword;
    private Sex uSex;
    private String userEmail;
    private Date userBirthday;

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Sex getuSex() {
        return uSex;
    }

    public void setuSex(Sex uSex) {
        this.uSex = uSex;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }
}
