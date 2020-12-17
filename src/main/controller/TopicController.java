package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.Topic;
import main.services.TopicServer;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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
    private TableView<Topic> topicTable;

    @FXML
    private TableColumn<Topic, Integer> topicID;

    @FXML
    private TableColumn<Topic, Integer> topicSectionID;

    @FXML
    private TableColumn<Topic, Integer> topicUserID;

    @FXML
    private TableColumn<Topic, Integer> topicReplyTime;

    @FXML
    private TableColumn<Topic, String> topicTitle;

    @FXML
    private TableColumn<Topic, String> topicInfo;

    @FXML
    private TableColumn<Topic, Timestamp> topicTime;

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

    public void initialize()
    {
        topicID.setCellValueFactory(new PropertyValueFactory<Topic,Integer>("topicID"));
        topicSectionID.setCellValueFactory(new PropertyValueFactory<Topic,Integer>("topicSectionID"));
        topicUserID.setCellValueFactory(new PropertyValueFactory<Topic,Integer>("topicUserID"));
        topicReplyTime.setCellValueFactory(new PropertyValueFactory<Topic,Integer>("topicReplyCount"));
        topicTitle.setCellValueFactory(new PropertyValueFactory<Topic,String>("topicTitle"));
        topicInfo.setCellValueFactory(new PropertyValueFactory<Topic,String>("topicContents"));
        topicTime.setCellValueFactory(new PropertyValueFactory<Topic,Timestamp>("topicTime"));

        TopicServer topicServer = new TopicServer();
        List<Topic> topicList = topicServer.getAllTopic();
        ObservableList<Topic> observableTopicList = FXCollections.observableList(topicList);
        topicTable.setItems(observableTopicList);

        topicTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTopicDetails(newValue));
    }

    public void showTopicDetails(Topic topic)
    {
        if (topic == null)
            return;
        else {
            inTopicID.setText(String.valueOf(topic.getTopicID()));
            inTopicSectionID.setText(String.valueOf(topic.getTopicSectionID()));
            inTopicUserID.setText(String.valueOf(topic.getTopicUserID()));
            inTopicReplyTime.setText(String.valueOf(topic.getTopicReplyCount()));
            inTopicTitle.setText(topic.getTopicTitle());
            inTopicInfo.setText(topic.getTopicContents());
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strTime = "";
            try {
                strTime = sdf.format(topic.getTopicTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
            inTopicTime.setText(strTime);
        }
    }
}
