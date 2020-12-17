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
import main.model.Reply;
import main.services.ReplyServer;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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
    private TableView<Reply> replyTable;

    @FXML
    private TableColumn<Reply, Integer> replyID;

    @FXML
    private TableColumn<Reply, Integer> replyTopicID;

    @FXML
    private TableColumn<Reply, Integer> replyUserID;

    @FXML
    private TableColumn<Reply, String> replyInfo;

    @FXML
    private TableColumn<Reply, Timestamp> replyTime;

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

    public void initialize()
    {
        replyID.setCellValueFactory(new PropertyValueFactory<Reply,Integer>("reID"));
        replyTopicID.setCellValueFactory(new PropertyValueFactory<Reply,Integer>("reTopicID"));
        replyUserID.setCellValueFactory(new PropertyValueFactory<Reply,Integer>("reUserID"));
        replyInfo.setCellValueFactory(new PropertyValueFactory<Reply,String>("reContents"));
        replyTime.setCellValueFactory(new PropertyValueFactory<Reply,Timestamp>("reTime"));

        ReplyServer replyServer = new ReplyServer();
        List<Reply> replyList = replyServer.getAllReply();
        ObservableList<Reply> observableReplyList = FXCollections.observableList(replyList);

        replyTable.setItems(observableReplyList);

        replyTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showReplyDetails(newValue));
    }

    public void showReplyDetails(Reply reply)
    {
        if (reply == null)
            return;
        else {
            inReplyID.setText(String.valueOf(reply.getReID()));
            inReplyTopicID.setText(String.valueOf(reply.getReTopicID()));
            inReplyUserID.setText(String.valueOf(reply.getReUserID()));
            inReplyInfo.setText(reply.getReContents());
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strTime = "";
            try {
                strTime = sdf.format(reply.getReTime());
            }catch (Exception e) {
                e.printStackTrace();
            }
            inReplyTime.setText(strTime);
        }
    }
}
