package main;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 15:18
 */

import javafx.scene.control.Alert;
import main.controller.MainController;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.controller.LoginController;
import main.services.AdminServer;
import main.tools.SimpleTools;

/**
 *
 * @author itwang
 */
public class MainAppAdmin extends Application {
    private Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 400.0;
    private final double MINIMUM_WINDOW_HEIGHT = 250.0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Admin Forum Manager System");
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        gotologin();
        stage.show();
    }



    public void gotologin(){
        try {
            LoginController login = (LoginController) replaceSceneContent("../resources/admin_login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(MainAppAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotomain(){
        try {
            MainController main = (MainController) replaceSceneContent("../resources/admin_main.fxml");
            main.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(MainAppAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void userlogin(int account,String password){
        AdminServer ad = new AdminServer();
        SimpleTools simpleTools = new SimpleTools();
        if(ad.adminLogin(account,password)){
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "登录成功！");
            gotomain();

        } else {
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "不正确！");
        }
    }
    public void useroutmain(){
        gotologin();
    }

    // 主界面
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MainAppAdmin.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MainAppAdmin.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 800, 732);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    // User管理界面
    public AnchorPane initUserManagerAddFrame(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../resources/UserManagerFrame.fxml"));
            AnchorPane root = loader.load();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AnchorPane initSectionManagerAddFrame(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../resources/SectionManagerFrame.fxml"));
            AnchorPane root = loader.load();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AnchorPane initTopicManagerAddFrame(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../resources/TopicManagerFrame.fxml"));
            AnchorPane root = loader.load();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AnchorPane initReplyManagerAddFrame(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../resources/ReplyManagerFrame.fxml"));
            AnchorPane root = loader.load();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}