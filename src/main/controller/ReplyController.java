package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-16 15:40
 */
public class ReplyController {
    @FXML
    private Button btAll;

    @FXML
    private TextField inQueryUserID;

    @FXML
    private Button btQuery;

    @FXML
    private TableView<?> replyTable;

    @FXML
    private TableColumn<?, ?> replyID;

    @FXML
    private TableColumn<?, ?> replyTopicID;

    @FXML
    private TableColumn<?, ?> replyUserID;

    @FXML
    private TableColumn<?, ?> replyInfo;

    @FXML
    private TableColumn<?, ?> replyTime;

    @FXML
    private TextField inReplyID;

    @FXML
    private TextField inReplyTopicID;

    @FXML
    private TextField inReplyUserID;

    @FXML
    private TextField inReplyTime;

    @FXML
    private TextArea inReplyInfo;

    @FXML
    private Button btAdd;

    @FXML
    private Button btUpdate;

    @FXML
    private Button btDel;
}
