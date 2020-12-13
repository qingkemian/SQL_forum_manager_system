package main.services;

import main.dao.TopicDao;
import main.model.Topic;

import java.sql.SQLException;
import java.util.List;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 19:59
 */
public class TopicServer {
    private TopicDao topicDao = new TopicDao();

    // 获取所有主题帖
    public List<Topic> getAllTopic()
    {
        try {
            return topicDao.getAllTopic();
        } catch (SQLException e) {
            System.out.println("TopicServer-getAllTopic Error:"+e);
            return null;
        }
    }

    // 创建新主题帖
    public boolean createTopic(Topic topic)
    {
        try {
            return topicDao.createTopic(topic);
        } catch (SQLException e) {
            System.out.println("TopicServer-createTopic Error:"+e);
            return false;
        }
    }

    // 删除主题帖
    public boolean deleteTopic(int topicID)
    {
        try {
            return topicDao.deleteTopic(topicID);
        } catch (SQLException e) {
            System.out.println("TopicServer-deleteTopic Error:"+e);
            return false;
        }
    }

    // 查询主题帖
    public List<Topic> queryTopicByTitle(String title)
    {
        try {
            return topicDao.getTopicByTitle(title);
        } catch (SQLException e) {
            System.out.println("SectionServer-queryTopicByTitle Error:"+e);
            return null;
        }
    }

    // 更新主题帖
    public boolean updateTopic(Topic topic)
    {
        try {
            return topicDao.updateTopic(topic);
        } catch (SQLException e) {
            System.out.println("SectionServer-updateTopic Error:"+e);
            return false;
        }
    }
}
