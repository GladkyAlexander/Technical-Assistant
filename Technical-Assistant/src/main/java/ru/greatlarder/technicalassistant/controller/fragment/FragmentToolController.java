package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemToolController;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Tool;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListTool;
import ru.greatlarder.technicalassistant.services.database.sqlite.tool.GetListToolAllSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class FragmentToolController implements ObserverLang, Initializable {
    @FXML public SplitPane splitPaneTool;
    @FXML public TabPane tabPaneTool;
    Company company;
    User user;
    String lang;

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLang(lang);
    }
    private void setLang(String lang){

    }
    public void loadFragment(){
        tabPaneTool.getTabs().clear();
        GetListTool getListTool = new GetListToolAllSQLite();
        List<Tool> toolList = getListTool.getListTool(user, company.getNameCompany(), null);
        Set<String> nameToolHashSet = new HashSet<>();

        for (Tool tool : toolList){
            nameToolHashSet.add(tool.getToolName());
        }

        for (String name : nameToolHashSet){
            List<Tool> toolList1 = new ArrayList<>();
            for(Tool tool : toolList){
                if(name.equals(tool.getToolName())){
                    toolList1.add(tool);
                }
            }
            tabPaneTool.getTabs().add(new Tab(company.getNameCompany(), createTableTool(toolList1)));
        }
    }

    private ListView<Tool> createTableTool(List<Tool> toolList1) {
        ObservableList<Tool> list = FXCollections.observableArrayList(toolList1);
        ListView<Tool> toolListView = new ListView<>(list);
        toolListView.setCellFactory(param -> new ListCell<>(){
            final FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_tool.fxml"));
            Node node;
            ItemToolController controller;
            {
                try {
                    node = loader.load();
                    controller = loader.getController();
                    setPrefWidth(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void updateItem(Tool item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                } else {
                    controller.setLabelToolName(item.getToolName());
                    controller.setLabelBrand(item.getToolBrand());
                    controller.setLabelSerialNumber(item.getToolSerialNumber());
                    controller.setLabelCondition(item.getToolCondition());
                    controller.setLabelStartOfOperation(item.getStartOfOperation().toString());
                    setGraphic(node);
                }
            }
        });
        return toolListView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        setLang(lang);
        loadFragment();
    }
}
