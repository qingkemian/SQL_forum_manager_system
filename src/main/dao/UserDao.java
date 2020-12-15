package main.dao;

import main.model.Reply;
import main.model.Section;
import main.model.Topic;
import main.model.User;
import main.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.List;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 13:14
 */
public class UserDao {

    // （查）在数据库中对用户登录身份验证 如果id和密码正确就返回true 否则返回false
    public boolean IdentifyAdministrator(User user) throws SQLException
    {
        QueryRunner runner= new QueryRunner(DBUtils.getDataSource());
        String sql="select * from u where userID=? and userPassword=?";
        User temp=runner.query(sql, new BeanHandler<User>(User.class),user.getUserID(),user.getUserPassword());
        if(temp!=null)
            return true;
        else
            return false;
    }

    // （查）从数据库中读取所有用户信息
    public List<User> getAllUser() throws SQLException
    {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from u";
        return runner.query(sql,new BeanListHandler<User>(User.class));
    }

    // （删/改）从数据库中删除用户
    public boolean deleteUser(int userID) throws SQLException
    {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());

        // 将该用户的所有回复 所有权改为 用户0
        // 选取reply表中该用户的所有记录
        String sql="select * from reply where reUserID=?";
        List<Reply> replyList=runner.query(sql,new BeanListHandler<Reply>(Reply.class),userID);
        // 将所有该用户的回复 的 用户id 更改为0
        for(int i=0;i<replyList.size();i++)
        {
            sql="update reply set reUserID=? where reReID=?";
            runner.execute(sql,0,replyList.get(i).getReID());
        }

        // 将该用户的所有主题帖 所有权改为 用户0
        // 选取topic表中该用户的所有记录
        sql="select * from topic where topicUserID=?";
        List<Topic> topicList=runner.query(sql,new BeanListHandler<Topic>(Topic.class),userID);
        // 将所有该用户的主题帖 的 贴主id 更改为0
        for(int i=0;i<topicList.size();i++)
        {
            sql="update topic set topicUserID=? where topicID=?";
            runner.execute(sql,0,topicList.get(i).getTopicID());
        }

        // 将该用户的所有板块 所有权改为 用户0
        // 选取section表中该用户的所有记录
        sql="select * from section where sectionMasterID=?";
        List<Section> sectionList=runner.query(sql,new BeanListHandler<Section>(Section.class),userID);
        // 将所有该用户为版主的版块 的 版主id 更改为0
        for(int i=0;i<sectionList.size();i++)
        {
            sql="update section set sectionMasterID=? where sectionID=?";
            runner.execute(sql,0,sectionList.get(i).getSectionID());
        }

        // 将改用户从u表删除
        sql="delete from u where userID=?";
        int result = runner.execute(sql,userID);
        return result>=1?true:false;
    }

    // （查）在数据库中通过用户id查找用户
    public User getUserByUserID(int userID) throws SQLException
    {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from u where userID=?";
        return runner.query(sql,new BeanHandler<User>(User.class),userID);
    }

    // （增）在数据库中创建新用户
    public boolean createUser(User user) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="insert into u values(?,?,?,?,?,?)";
        int result = runner.execute(sql,user.getUserID(),user.getUserName(),user.getUserPassword(),user.getUserSex(),user.getUserEmail(),user.getUserBirthday());
        return result>=1?true:false;
    }

    // （改）在数据库中更改用户信息
    public boolean updateUser(User user) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="update u set userName=?,userPassword=? where userID=?";
        int result = runner.execute(sql,user.getUserName(),user.getUserPassword(),user.getUserID());
        return result>=1?true:false;
    }
}
