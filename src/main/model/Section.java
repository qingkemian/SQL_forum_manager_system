package main.model;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 14:41
 */
public class Section {
    private int sectionID;
    private String sectionName;
    private int sectionMasterID;
    private String sectionStatement;

    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public int getSectionMasterID() {
        return sectionMasterID;
    }

    public void setSectionMasterID(int sectionMasterID) {
        this.sectionMasterID = sectionMasterID;
    }

    public String getSectionStatement() {
        return sectionStatement;
    }

    public void setSectionStatement(String sectionStatement) {
        this.sectionStatement = sectionStatement;
    }
}
