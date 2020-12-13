package main.model;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 14:41
 */
public class Section {
    private int sID;
    private String sName;
    private int sMasterID;
    private String sStatement;

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getsMasterID() {
        return sMasterID;
    }

    public void setsMasterID(int sMasterID) {
        this.sMasterID = sMasterID;
    }

    public String getsStatement() {
        return sStatement;
    }

    public void setsStatement(String sStatement) {
        this.sStatement = sStatement;
    }
}
