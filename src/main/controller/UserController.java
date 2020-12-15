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
        // 映射数据进每列
        userID.setCellValueFactory(new PropertyValueFactory<User,Integer>("userID"));
        userName.setCellValueFactory(new PropertyValueFactory<User,String>("userName"));
        userPw.setCellValueFactory(new PropertyValueFactory<User,String>("userPassword"));
        userSex.setCellValueFactory(new PropertyValueFactory<User, User.Sex>("userSex"));
        userMail.setCellValueFactory(new PropertyValueFactory<User,String>("userEmail"));
        userBir.setCellValueFactory(new PropertyValueFactory<User,Date>("userBirthday"));

        // 从数据库中获取所有User信息，将其转换为ObservableList
        UserServer userServer = new UserServer();
        List<User> userlist = userServer.getAllUser();
        ObservableList<User> observableUserList = FXCollections.observableList(userlist);
        // 将其添加到tableview中
        userTable.setItems(observableUserList);
    }
}
