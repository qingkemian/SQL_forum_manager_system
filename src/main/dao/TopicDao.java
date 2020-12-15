package main.dao;

import main.model.Reply;
import main.model.Topic;
import main.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 14:58
 */
public class TopicDao {
    // 从数据库中查询所有主题帖
    public List<Topic> getAllTopic() throws SQLException
    {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from topic";
        return runner.query(sql,new BeanListHandler<Topic>(Topic.class));
    }

    // 从数据库中删除版块
    public boolean deleteTopic(int topicID) throws SQLException
    {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());

        // 将该主题帖下的回复帖都归类到 主题帖0
        // 选取该主题帖下所有回复帖
        String sql="select * from reply where replyTopicID=?";
        List<Reply> replyList=runner.query(sql,new BeanListHandler<Reply>(Reply.class),topicID);
        // 更改回复帖归属
        for(int i=0;i<replyList.size();i++)
        {
            sql="update reply set replyTopicID=? where replyID=?";
            runner.execute(sql,0,replyList.get(i).getReID());
        }

        // 删除该主题帖
        sql="delete from topic where topicID=?";
        int result = runner.execute(sql,topicID);
        return result>=1?true:false;
    }

    // 在数据库中新建主题帖
    public boolean createTopic(Topic topic) throws SQLException
    {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="insert into topic values(?,?,?,?,?,?,?);";
        int result=runner.execute(sql,topic.getTopicID(),topic.getTopicSectionID(),topic.getTopicID(),topic.getTopicReplyCount(),topic.getTopicTitle(),topic.getTopicContents(),topic.getTopicTime());
        return result>=1?true:false;
    }

    // 在数据库中修改主题帖
    public boolean updateTopic(Topic topic) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="update topic set topicSectionID=?,topicReplyCount=?,topicTitle=?,topicContents=? where sectionID=?";
        int result=runner.execute(sql,topic.getTopicSectionID(),topic.getTopicReplyCount(),topic.getTopicTitle(),topic.getTopicContents(),topic.getTopicID());
        return result>=1?true:false;
    }

    // 从数据库中查询主题帖
    public List<Topic> getTopicByTitle(String title) throws SQLException
    {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from topic where topicTitle=?";
        return runner.query(sql,new BeanListHandler<Topic>(Topic.class),title);
    }
}
