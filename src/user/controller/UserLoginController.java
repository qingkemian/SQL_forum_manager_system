package user.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import user.MainAppUser;
import user.tools.UserSimpleTools;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-16 15:45
 */
public class UserLoginController  implements Initializable {
        @FXML
        private TextField account;

        @FXML
        private PasswordField password;
        private MainAppUser application;

        private UserSimpleTools simpleTools = new UserSimpleTools();

        public void setApp(MainAppUser application){
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
