package main.services;

import main.dao.ReplyDao;
import main.model.Reply;
import main.model.Topic;

import java.sql.SQLException;
import java.util.List;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 20:29
 */
public class ReplyServer {
    private ReplyDao replyDao = new ReplyDao();

    // 获取所有回复贴
    public List<Reply> getAllReply()
    {
        try {
            return replyDao.getAllReply();
        } catch (SQLException e) {
            System.out.println("ReplyServer-getAllReply Error:"+e);
            return null;
        }
    }

    // 创建新回复帖
    public boolean createReply(Reply reply)
    {
        try {
            return replyDao.createReply(reply);
        } catch (SQLException e) {
            System.out.println("ReplyServer-createReply Error:"+e);
            return false;
        }
    }

    // 删除回复帖
    public boolean deleteReply(int replyID)
    {
        try {
            return replyDao.deleteReply(replyID);
        } catch (SQLException e) {
            System.out.println("ReplyServer-deleteReply Error:"+e);
            return false;
        }
    }

    // 查询主题帖
    public List<Reply> queryReplyByUserID(int uID)
    {
        try {
            return replyDao.getReplyByUserID(uID);
        } catch (SQLException e) {
            System.out.println("ReplyServer-queryReplyByUserID Error:"+e);
            return null;
        }
    }

    // 更新主题帖
    public boolean updateReply(Reply reply)
    {
        try {
            return replyDao.updateReply(reply);
        } catch (SQLException e) {
            System.out.println("ReplyServer-updateReply Error:"+e);
            return false;
        }
    }
}
