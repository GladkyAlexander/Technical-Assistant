package ru.greatlarder.technicalassistant.services.tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemTaskController;
import ru.greatlarder.technicalassistant.domain.Affairs;

import java.io.IOException;
import java.util.List;

public class TableViewTask {
    
    public ListView<Affairs> upBox(List<Affairs> taskListIn){
        ObservableList<Affairs> observableList = FXCollections.observableArrayList(taskListIn);
        //observableList.addAll(taskListIn);
        ListView<Affairs> list = new ListView<>(observableList);

        list.setCellFactory(param -> new ListCell<>(){
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_task.fxml"));
            Node node;
            ItemTaskController listItemTaskController;
            {
                try {
                    node = loader.load();
                    listItemTaskController = loader.getController();
                    setPrefWidth(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void updateItem(Affairs task, boolean b) {
                super.updateItem(task, b);
                if(b){
                    setGraphic(null);
                } else {
                    listItemTaskController.setLabelDate(String.valueOf(task.getDateOfCreation()));
                    listItemTaskController.setLabelTime(String.valueOf(task.getTimeOfCreation()));
                    listItemTaskController.setLabelRoom(task.getRoom());
                    listItemTaskController.setLabelCreator(task.getCreator());
                    listItemTaskController.setTextAreaTask(task.getTextTask());
                    listItemTaskController.setAffairs(task);
                    listItemTaskController.setImgCompletion(task.getStatus());
                    
                    setGraphic(node);
                }
            }
        });

        return list;
    }

}
