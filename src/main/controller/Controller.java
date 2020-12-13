package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.show.ShowUserList;

import javafx.event.ActionEvent;

public class Controller {

    public void initialize() {

    }



    @FXML
    void showmess(ActionEvent event){
        ShowUserList userlist = new ShowUserList();
        userlist.show();
    }
}
