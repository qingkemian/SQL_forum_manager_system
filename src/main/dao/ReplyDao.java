package main.dao;

import main.model.Reply;
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
 * @日期:2020-12-13 14:57
 */
public class ReplyDao {
    // （应该不会用到）从数据库中查询所有回复帖
    public List<Reply> getAllReply() throws SQLException
    {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from reply";
        return runner.query(sql,new BeanListHandler<Reply>(Reply.class));
    }

    // 从数据库中删除回复帖
    public boolean deleteReply(int replyID) throws SQLException
    {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="delete from reply where reID=?";
        int result = runner.execute(sql,replyID);

        // topic中回帖数-1
        Reply theReply;
        theReply = getReplyByReplyID(replyID);
        int id = theReply.getReTopicID();
        Topic theTopic;
        TopicDao topicDao = new TopicDao();
        theTopic =  topicDao.getTopicByID(id);
        int reCount = theTopic.getTopicReplyCount();

        reCount -= 1;

        sql = "update topic set topicReplyCount=? where topicID=?";
        int res = runner.execute(sql,reCount,id);
        return result>=1?true:false;
    }

    // 在数据库中新建回复帖
    public boolean createReply(Reply reply) throws SQLException
    {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="insert into reply values(?,?,?,?,?);";
        int result=runner.execute(sql,reply.getReID(),reply.getReTopicID(),reply.getReUserID(),reply.getReContents(),reply.getReTime());

        // topic中回帖数+1
        Topic theTopic;
        TopicDao topicDao = new TopicDao();
        theTopic =  topicDao.getTopicByID(reply.getReTopicID());
        int reCount = theTopic.getTopicReplyCount();

        reCount += 1;

        sql = "update topic set topicReplyCount=? where topicID=?";
        int res = runner.execute(sql,reCount,reply.getReTopicID());

        return result>=1?true:false;
    }

    // 在数据库中修改回复帖
    public boolean updateReply(Reply reply) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="update reply set reTopicID=?,reUserID=?,reContents=?,reTime=? where reID=?";
        int result=runner.execute(sql,reply.getReTopicID(),reply.getReUserID(),reply.getReContents(),reply.getReTime(),reply.getReID());
        return result>=1?true:false;
    }

    // 在数据库中查询某个用户所有回帖
    public List<Reply> getReplyByUserID(int uID) throws SQLException
    {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from reply where reUserID=?";
        return runner.query(sql,new BeanListHandler<Reply>(Reply.class),uID);
    }

    // 在数据库中通过帖子id查询所有回帖
    public Reply getReplyByReplyID(int reID) throws SQLException
    {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from reply where reID=?";
        return runner.query(sql,new BeanHandler<Reply>(Reply.class),reID);
    }
}
