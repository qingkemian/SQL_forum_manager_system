package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.User;
import main.services.UserServer;
import main.tools.SimpleTools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
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

    @FXML
    private TextField inQueryUserID;

    @FXML
    private TextField inUserID;

    @FXML
    private TextField inUserName;

    @FXML
    private TextField inUserPw;

    @FXML
    private RadioButton rbMale;

    @FXML
    private ToggleGroup sex;

    @FXML
    private RadioButton rbFemale;


    @FXML
    private TextField inUserEmail;

    @FXML
    private TextField inUserBir;

    // 初始化
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

        // 为tableview添加事件监听器（选中展示细节）
        userTable.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> showUserDetails(newValue));
    }

    // 展示所有用户
    public void show_all(ActionEvent event)
    {
        // 从数据库中获取所有User信息，将其转换为ObservableList
        UserServer userServer = new UserServer();
        List<User> userlist = userServer.getAllUser();
        ObservableList<User> observableUserList = FXCollections.observableList(userlist);
        // 将其添加到tableview中
        userTable.setItems(observableUserList);
    }

    // 新建user用户
    public void add_bt_event(ActionEvent event)
    {
        String userIdText = inUserID.getText();
        String userNameText = inUserName.getText();
        String userPwText = inUserPw.getText();
        User.Sex userSexText = null;
        if (rbMale.isSelected())
            userSexText = User.Sex.male;
        else if (rbFemale.isSelected())
            userSexText = User.Sex.female;
        String userEmText = inUserEmail.getText();
        String userBirText = inUserBir.getText();

        SimpleTools simpleTools = new SimpleTools();

        try {
            // 处理数据 将id转成int 将bir转成date
            int id = Integer.parseInt(userIdText);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date birthdate = null;
            birthdate = df.parse(userBirText);

            // 将信息传入user中
            User newUser = new User();
            newUser.setUserID(id);
            newUser.setUserName(userNameText);
            newUser.setUserPassword(userPwText);
            newUser.setUserSex(userSexText);
            newUser.setUserEmail(userEmText);
            newUser.setUserBirthday(birthdate);

            UserServer userServer = new UserServer();
            boolean newflag = userServer.createUser(newUser);

            if (newflag) {
                simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "添加成功！");
                // 从数据库中获取所有User信息，将其转换为ObservableList
                List<User> userlist = userServer.getAllUser();
                ObservableList<User> observableUserList = FXCollections.observableList(userlist);
                // 将其添加到tableview中
                userTable.setItems(observableUserList);
            } else {
                simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "添加失败 请检查id！");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "请输入合法id！");
        } catch (ParseException e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "请输入合法日期！");
        }
    }

    // 如果表格行被选中，则将数据显示在下面的文本框中
    public void showUserDetails(User user)
    {
        if (user == null)
            return;
        else {
            inUserID.setText(String.valueOf(user.getUserID()));
            inUserName.setText(user.getUserName());
            inUserPw.setText(user.getUserPassword());
            if (((user.getUserSex()).toString()).equals("male")) {
                rbMale.setSelected(true);
            } else {
                rbFemale.setSelected(true);
            }
            inUserEmail.setText(user.getUserEmail());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            inUserBir.setText(simpleDateFormat.format(user.getUserBirthday()));
        }
    }

    // 查询按钮事件监听器
    public void query_bt_event(ActionEvent event)
    {
        String queryUserID = inQueryUserID.getText();
        try {
            int id = Integer.parseInt(queryUserID);
            // 从数据库中获取所有User信息，将其转换为ObservableList
            UserServer userServer = new UserServer();
            User user = userServer.queryUserByUserID(id);
            if (user != null)
            {
                List<User> userlist = new ArrayList<>();
                userlist.add(user);
                ObservableList<User> observableUserList = FXCollections.observableList(userlist);
                // 将其添加到tableview中
                userTable.setItems(observableUserList);
            } else {
                SimpleTools simpleTools = new SimpleTools();
                simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "未查询到用户！");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            SimpleTools simpleTools = new SimpleTools();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "请输入合法id！");
        }
    }

    // 修改按钮事件监听器
    public void update_bt_event(ActionEvent event)
    {
        String updateUserID = inUserID.getText();
        String updateUserName = inUserName.getText();
        String updateUserPw = inUserPw.getText();

        User.Sex updateUserSex = null;
        if (rbMale.isSelected())
            updateUserSex = User.Sex.male;
        else if (rbFemale.isSelected())
            updateUserSex = User.Sex.female;
        String updateUserEm = inUserEmail.getText();
        String updateUserBir = inUserBir.getText();

        SimpleTools simpleTools = new SimpleTools();
        try {
            int id = Integer.parseInt(updateUserID.trim());

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date birdate = null;
            birdate = df.parse(updateUserBir);

            // 将修改信息写入User中 进行更新
            UserServer userServer = new UserServer();
            User udUser = new User();
            udUser.setUserID(id);
            udUser.setUserName(updateUserName);
            udUser.setUserPassword(updateUserPw);
            udUser.setUserSex(updateUserSex);
            udUser.setUserEmail(updateUserEm);
            udUser.setUserBirthday(birdate);

            // 更新并返回结果
            boolean udfalg = userServer.updateUser(udUser);

            if (udfalg)
            {
                simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "修改成功！");
                // 从数据库中获取所有User信息，将其转换为ObservableList
                List<User> userlist = userServer.getAllUser();
                ObservableList<User> observableUserList = FXCollections.observableList(userlist);
                // 将其添加到tableview中
                userTable.setItems(observableUserList);
            } else {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "修改失败！");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "请输入合法id！");
        } catch (ParseException e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "请输入合法日期！");
        }
     }

     // 删除按钮事件监听器
    public void delete_bt_event(ActionEvent event) {
        String delUserID = inUserID.getText();

        SimpleTools simpleTools = new SimpleTools();

        try {
            int id = Integer.parseInt(delUserID.trim());

            // 删除用户
            UserServer userServer = new UserServer();
            boolean delflag = userServer.deleteUser(id);

            if (delflag) {
                simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "删除成功！");
                // 从数据库中获取所有User信息，将其转换为ObservableList
                List<User> userlist = userServer.getAllUser();
                ObservableList<User> observableUserList = FXCollections.observableList(userlist);
                // 将其添加到tableview中
                userTable.setItems(observableUserList);
            } else {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "删除失败！");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "请输入合法id！");
        }
    }
}
