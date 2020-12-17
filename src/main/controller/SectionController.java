package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.Section;
import main.services.SectionServer;

import java.util.List;

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
    private TableView<Section> sectionTable;

    @FXML
    private TableColumn<Section, Integer> sectionID;

    @FXML
    private TableColumn<Section, String> sectionName;

    @FXML
    private TableColumn<Section, Integer> sectionMasterID;

    @FXML
    private TableColumn<Section, String> sectionInfo;

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

    // 初始化
    public void initialize(){
        // 映射数据
        sectionID.setCellValueFactory(new PropertyValueFactory<Section,Integer>("sectionID"));
        sectionName.setCellValueFactory(new PropertyValueFactory<Section,String>("sectionName"));
        sectionMasterID.setCellValueFactory(new PropertyValueFactory<Section,Integer>("sectionMasterID"));
        sectionInfo.setCellValueFactory(new PropertyValueFactory<Section,String>("sectionStatement"));

        // 获取数据
        SectionServer sectionServer = new SectionServer();
        List<Section> sectionList = sectionServer.getAllSection();
        ObservableList<Section> observableSectionList = FXCollections.observableList(sectionList);
        // 添加到tableview
        sectionTable.setItems(observableSectionList);

        sectionTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showSectionDetails(newValue));
    }

    // 选中，将其显示
    public void showSectionDetails(Section section)
    {
        if (section == null)
            return;
        else {
            inSectionID.setText(String.valueOf(section.getSectionID()));
            inSectionName.setText(section.getSectionName());
            inSectionMasterID.setText(String.valueOf(section.getSectionMasterID()));
            inSectionInfo.setText(section.getSectionStatement());
        }
    }
}
