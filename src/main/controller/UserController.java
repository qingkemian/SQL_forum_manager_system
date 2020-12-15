package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.User;
import main.services.UserServer;

import java.util.Date;
import java.util.List;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-15 11:00
 */
public class UserController {
    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, Integer> userID;

    @FXML
    private TableColumn<User, String> userName;

    @FXML
    private TableColumn<User, String> userPw;

    @FXML
    private TableColumn<User, User.Sex> userSex;

    @FXML
    private TableColumn<User, String> userMail;

    @FXML
    private TableColumn<User, Date> userBir;

    public void initialize()
    {
        System.out.println("hello");

        UserServer userServer = new UserServer();

        List<User> userList = userServer.getAllUser();

        if(userList != null)
        {
            for (int j=0;j<userList.size();j++)
            {
                System.out.println("id"+userList.get(j).getUserID());
                System.out.println("name"+userList.get(j).getUserName());
                System.out.println("sex"+userList.get(j).getUserSex());
            }
        }
        else
        {
            System.out.println("null");
        }


        // 映射数据进每列
        userID.setCellValueFactory(new PropertyValueFactory<User,Integer>("userID"));
        userName.setCellValueFactory(new PropertyValueFactory<User,String>("userName"));
        userPw.setCellValueFactory(new PropertyValueFactory<User,String>("userPassword"));
        userSex.setCellValueFactory(new PropertyValueFactory<User, User.Sex>("userSex"));
        userMail.setCellValueFactory(new PropertyValueFactory<User,String>("userEmail"));
        userBir.setCellValueFactory(new PropertyValueFactory<User,Date>("userBirthday"));

        List<User> userlist = userServer.getAllUser();
        ObservableList<User> observableUserList = FXCollections.observableList(userlist);
        userTable.setItems(observableUserList);


        System.out.println(observableUserList.get(1).getUserID());

        System.out.println(observableUserList.get(1).getUserSex());
    }
}
