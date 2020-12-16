package user.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import user.MainAppUser;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-16 15:45
 */
public class UserMainController implements Initializable {
    private MainAppUser application;

    @FXML
    private AnchorPane mainFrameAnchorPane;

    public void setApp(MainAppUser application){
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
