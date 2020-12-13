package main.controller;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 15:20
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.AdminLogin;

/**
 *
 * @author itwang
 */
public class MainController implements Initializable{
    private AdminLogin application;

    public void setApp(AdminLogin application){
        this.application = application;
    }

    @FXML
    private void OUT_M(ActionEvent event) {
        application.useroutmain();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}