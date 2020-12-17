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
 * @日期:2020-12-16 15:39
 */
public class TopicController {
    @FXML
    private Button btAll;

    @FXML
    private TextField inQueryTopicName;

    @FXML
    private Button btQuery;

    @FXML
    private TableView<?> topicTable;

    @FXML
    private TableColumn<?, ?> topicID;

    @FXML
    private TableColumn<?, ?> topicSectionID;

    @FXML
    private TableColumn<?, ?> topicUserID;

    @FXML
    private TableColumn<?, ?> topicReplyTime;

    @FXML
    private TableColumn<?, ?> topicTitle;

    @FXML
    private TableColumn<?, ?> topicInfo;

    @FXML
    private TableColumn<?, ?> topicTime;

    @FXML
    private TextField inTopicID;

    @FXML
    private TextField inTopicSectionID;

    @FXML
    private TextField inTopicUserID;

    @FXML
    private TextField inTopicReplyTime;

    @FXML
    private TextField inTopicTitle;

    @FXML
    private TextField inTopicTime;

    @FXML
    private TextArea inTopicInfo;

    @FXML
    private Button btAdd;

    @FXML
    private Button btUpdate;

    @FXML
    private Button btDel;
}
