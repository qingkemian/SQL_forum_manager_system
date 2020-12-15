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

    private int userID;
    private String userName;
    private String userPassword;
    private Sex userSex;
    private String userEmail;
    private Date userBirthday;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Sex getUserSex() {
        return userSex;
    }

    public void setUserSex(Sex userSex) {
        this.userSex = userSex;
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
