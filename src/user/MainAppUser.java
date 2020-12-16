package user;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.services.AdminServer;
import user.controller.UserLoginController;
import user.controller.UserMainController;
import user.tools.UserSimpleTools;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-16 15:44
 */
public class MainAppUser extends Application {
    private Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 400.0;
    private final double MINIMUM_WINDOW_HEIGHT = 250.0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("User Forum");
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        gotouserlogin();
        stage.show();
    }



    public void gotouserlogin(){
        try {
            UserLoginController login = (UserLoginController) replaceSceneContent("../user_resources/user_login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(MainAppUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotousermain(){
        try {
            UserMainController main = (UserMainController) replaceSceneContent("../user_resources/user_main.fxml");
            main.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(MainAppUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void userlogin(int account,String password){
        AdminServer ad = new AdminServer();
        UserSimpleTools simpleTools = new UserSimpleTools();
        if(ad.adminLogin(account,password)){
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "登录成功！");
            gotousermain();
        } else {
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "不正确！");
        }
    }
    public void useroutmain(){
        gotouserlogin();
    }

    // 主界面
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();

        InputStream in = MainAppUser.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MainAppUser.class.getResource(fxml));
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


    public static void main(String[] args) {
        launch(args);
    }
}
