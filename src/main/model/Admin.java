package main.model;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 14:41
 */
public class Admin {
    private int adminID;
    private String adminName;
    private String adminPassword;

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
