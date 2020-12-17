package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-16 15:40
 */
public class SectionController {
    @FXML
    private Button btAll;

    @FXML
    private TextField inQuerySectionName;

    @FXML
    private Button btQuery;

    @FXML
    private TableView<?> sectionTable;

    @FXML
    private TableColumn<?, ?> sectionID;

    @FXML
    private TableColumn<?, ?> sectionName;

    @FXML
    private TableColumn<?, ?> sectionMasterID;

    @FXML
    private TableColumn<?, ?> sectionInfo;

    @FXML
    private TextField inSectionID;

    @FXML
    private TextField inSectionName;

    @FXML
    private TextField inSectionMasterID;

    @FXML
    private TextArea inSectionInfo;

    @FXML
    private Button btAdd;

    @FXML
    private Button btUpdate;

    @FXML
    private Button btDel;
}
