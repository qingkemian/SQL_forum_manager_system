package main.controller;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 15:15
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.MainAppAdmin;
import main.tools.SimpleTools;

/**
 *
 * @author itwang
 */
public class LoginController implements Initializable {
    @FXML private TextField account;
    @FXML private PasswordField password;
    private MainAppAdmin application;

   private SimpleTools simpleTools = new SimpleTools();

    public void setApp(MainAppAdmin application){
        this.application = application;
    }

    @FXML
    public void LOGIN_M(ActionEvent event) {
        try{
            application.userlogin(Integer.parseInt(account.getText()), password.getText());
        }catch (Exception e)
        {
            simpleTools.informationDialog(Alert.AlertType.WARNING,"提示", "警告", "请按要求输入! 用户id只能有数字！");
        }
    }

    @FXML
    private void CLEAR_M(ActionEvent event) {
        account.setText(null);
        password.setText(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}