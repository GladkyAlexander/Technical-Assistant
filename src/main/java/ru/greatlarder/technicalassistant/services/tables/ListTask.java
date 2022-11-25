package ru.greatlarder.technicalassistant.services.tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemTaskController;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Task;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;

import java.io.IOException;
import java.util.List;

public class ListTask implements ObserverLang, ObserverCompany {

    private String lang;
    private Company company;
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().handlerLang;
    HandlerCompanyListener handlerCompanyListener = GlobalLinkStartEngineerController.getStartEngineerController().handlerCompanyListener;
    public ListView<Task> upBox(List<Task> taskListIn){
        ObservableList<Task> observableList = FXCollections.observableArrayList();

        observableList.addAll(taskListIn);
        ListView<Task> list = new ListView<>(observableList);

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
            protected void updateItem(Task task, boolean b) {
                super.updateItem(task, b);
                if(b){
                    setGraphic(null);
                } else {

                    handlerLang.registerObserverLang(listItemTaskController);
                    handlerCompanyListener.registerObserverCompany(listItemTaskController);

                    ((ObserverLang) listItemTaskController).updateLang(new DataLang(lang));
                    ((ObserverCompany) listItemTaskController).updateCompany(new DataCompany(company));

                    listItemTaskController.setLabelDate(String.valueOf(task.getDateOfCreation()));
                    listItemTaskController.setLabelTime(String.valueOf(task.getTimeOfCreation()));
                    listItemTaskController.setLabelRoom(task.getRoom());
                    listItemTaskController.setLabelCreator(task.getCreator());
                    listItemTaskController.setTextAreaTask(task.getTextTask());
                    listItemTaskController.setTask(task);
                    listItemTaskController.setImgCompletion(task.getStatus());
                    listItemTaskController.setCompany(company);

                    setGraphic(node);
                }
            }
        });

        return list;
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
    }
}
