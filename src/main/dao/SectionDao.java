package main.dao;

import main.model.Section;
import main.model.Topic;
import main.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 14:58
 */
public class SectionDao {
    // 从数据库中查询所有版块
    public List<Section> getAllSection() throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from section";
        return runner.query(sql,new BeanListHandler<Section>(Section.class));
    }

    // 从数据库中删除版块
    public boolean deleteSection(int sectionID) throws SQLException
    {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());

        // 将该版块下的主题帖都归类到 版块0
        // 选取该版块下所有主题帖
        String sql="select * from topic where topicSectionID=?";
        List<Topic> topicList=runner.query(sql,new BeanListHandler<Topic>(Topic.class),sectionID);
        // 更改主题帖归属
        for(int i=0;i<topicList.size();i++)
        {
            sql="update topic set topicSectionID=? where topicID=?";
            runner.execute(sql,0,topicList.get(i).getTopicID());
        }

        // 删除该版块
        sql="delete from section where sectionID=?";
        int result = runner.execute(sql,sectionID);
        return result>=1?true:false;
    }

    // 在数据库中新建版块
    public boolean createSection(Section section) throws SQLException
    {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="insert into section values(?,?,?,?);";
        int result=runner.execute(sql,section.getSectionID(),section.getSectionName(),section.getSectionMasterID(),section.getSectionStatement());
        return result>=1?true:false;
    }

    // 在数据库中修改版块
    public boolean updateSection(Section section) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="update section set sectionName=?,sectionMasterID=?,sectionStatement=? where sectionID=?";
        int result=runner.execute(sql,section.getSectionName(),section.getSectionMasterID(),section.getSectionStatement(),section.getSectionID());
        return result>=1?true:false;
    }

    // 从数据库中查询版块
    public List<Section> getSectionBySectionName(String sectionName) throws SQLException
    {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from section where sectionName=?";
        return runner.query(sql,new BeanListHandler<Section>(Section.class),sectionName);
    }
}
