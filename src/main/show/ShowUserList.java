package main.show;

import main.model.User;
import main.services.UserServer;

import java.util.List;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 14:39
 */
public class ShowUserList {
    private UserServer userServer = new UserServer();

    public void show(){
        List<User> userList= userServer.getAllUser();

        if(userList != null)
        {
            for (int j=0;j<userList.size();j++)
            {
                System.out.println(userList.get(j).getuID());
                System.out.println(userList.get(j).getUserName());
                System.out.println(userList.get(j).getuSex());
            }
        }
        else
        {
            System.out.println("null");
        }
    }
}
