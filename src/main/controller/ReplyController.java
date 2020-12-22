package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.Reply;
import main.services.ReplyServer;
import main.tools.SimpleTools;

import javafx.event.ActionEvent;
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

    // 展示细节
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

    // 展示全部
    public void show_all(ActionEvent event)
    {
        ReplyServer replyServer = new ReplyServer();
        List<Reply> replyList = replyServer.getAllReply();
        ObservableList<Reply> observableReplyList = FXCollections.observableList(replyList);

        replyTable.setItems(observableReplyList);
    }

    // 添加
    public void add_bt_event(ActionEvent event)
    {
        String reIdText = inReplyID.getText();
        String reTopicIdText = inReplyTopicID.getText();
        String reUserIdText = inReplyUserID.getText();
        String reConText = inReplyInfo.getText();
        String reTimeText = inReplyTime.getText();

        SimpleTools simpleTools = new SimpleTools();

        try {
            int reid = Integer.parseInt(reIdText);
            int retopid = Integer.parseInt(reTopicIdText);
            int reuserid = Integer.parseInt(reUserIdText);
            Timestamp time = Timestamp.valueOf(reTimeText);

            Reply newReply = new Reply();
            newReply.setReID(reid);
            newReply.setReTopicID(retopid);
            newReply.setReUserID(reuserid);
            newReply.setReContents(reConText);
            newReply.setReTime(time);

            ReplyServer replyServer = new ReplyServer();
            boolean newflag = replyServer.createReply(newReply);

            if (newflag) {
                simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "添加成功！");
                List<Reply> replyList = replyServer.getAllReply();
                ObservableList<Reply> observableReplyList = FXCollections.observableList(replyList);

                replyTable.setItems(observableReplyList);
            } else {
                simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "添加失败 请检查输入！");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "请输入合法id！");
        } catch (Exception e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "请输入合法时间！");
        }
    }

    public void query_bt_event(ActionEvent event)
    {
        String queryByUserID = inQueryUserID.getText();

        try {
            int id = Integer.parseInt(queryByUserID);

            ReplyServer replyServer = new ReplyServer();

            List<Reply> replyList = replyServer.queryReplyByUserID(id);

            if(replyList != null)
            {
                ObservableList<Reply> observableReplyList = FXCollections.observableList(replyList);

                replyTable.setItems(observableReplyList);
            } else {
                SimpleTools simpleTools = new SimpleTools();
                simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "未查询到相关信息！");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            SimpleTools simpleTools = new SimpleTools();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "请输入合法id！");
        }
    }
}
