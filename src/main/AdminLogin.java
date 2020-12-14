package main;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 15:18
 */

import main.controller.MainController;

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

/**
 *
 * @author itwang
 */
public class AdminLogin extends Application {
    private Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 400.0;
    private final double MINIMUM_WINDOW_HEIGHT = 250.0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("FXML Login Sample");
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
            Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotomain(){
        try {
            MainController main = (MainController) replaceSceneContent("../resources/admin_main.fxml");
            main.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void userlogin(int account,String password){
        AdminServer ad = new AdminServer();
        if(ad.adminLogin(account,password)){
            gotomain();
        }
    }
    public void useroutmain(){
        gotologin();
    }
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = AdminLogin.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(AdminLogin.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 638, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
    public static void main(String[] args) {
        launch(args);
    }
}