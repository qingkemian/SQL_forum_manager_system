package main;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 15:18
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/sample.fxml"));
        primaryStage.setTitle("CZL");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.initStyle(StageStyle.UNDECORATED);  // 设置窗体无边框
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
