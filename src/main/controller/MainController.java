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
import javafx.scene.layout.AnchorPane;
import main.MainAppAdmin;


/**
 *
 * @author itwang
 */
public class MainController implements Initializable{
    private MainAppAdmin application;

    @FXML
    private AnchorPane mainFrameAnchorPane;

    public void setApp(MainAppAdmin application){
        this.application = application;
    }

    @FXML
    private void OUT_M(ActionEvent event) {
        application.useroutmain();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void do_userManager_event(ActionEvent event){
        AnchorPane pane = new MainAppAdmin().initUserManagerAddFrame();
        mainFrameAnchorPane.getChildren().clear();
        mainFrameAnchorPane.getChildren().add(pane);
    }

}