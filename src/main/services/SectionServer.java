package main.services;

import main.dao.SectionDao;
import main.model.Section;

import java.sql.SQLException;
import java.util.List;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 19:08
 */
public class SectionServer {
    private SectionDao sectionDao = new SectionDao();

    // 获取所有版块
    public List<Section> getAllSection()
    {
        try {
            return sectionDao.getAllSection();
        } catch (SQLException e) {
            System.out.println("SectionServer-getAllSection Error:"+e);
            return null;
        }
    }

    // 创建新版块
    public boolean createSectin(Section section)
    {
        try {
            return sectionDao.createSection(section);
        } catch (SQLException e) {
            System.out.println("SectionServer-createSectin Error:"+e);
            return false;
        }
    }

    // 删除版块
    public boolean deleteSectin(int sectionID)
    {
        try {
            return sectionDao.deleteSection(sectionID);
        } catch (SQLException e) {
            System.out.println("SectionServer-deleteSectin Error:"+e);
            return false;
        }
    }

    // 查询版块
    public List<Section> queryStafffBySectinName(String sectionName)
    {
        try {
            return sectionDao.getSectionBySectionName(sectionName);
        } catch (SQLException e) {
            System.out.println("SectionServer-queryStafffBySectinName Error:"+e);
            return null;
        }
    }

    // 更新版块
    public boolean updateSectin(Section section)
    {
        try {
            return sectionDao.updateSection(section);
        } catch (SQLException e) {
            System.out.println("SectionServer-updateSectin Error:"+e);
            return false;
        }
    }
}
