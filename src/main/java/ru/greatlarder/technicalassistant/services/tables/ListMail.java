package ru.greatlarder.technicalassistant.services.tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemMailController;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ListMail implements ObserverLang, ObserverCompany {

    HandlerLang handlerLang = GlobalLinkMainController.getMainController().handlerLang;
    HandlerCompanyListener handlerCompanyListener = GlobalLinkStartEngineerController.getStartEngineerController().handlerCompanyListener;
    String lang;
    Company company;
    public ListView<Task> upBox(List<Task> taskListIn){
        ObservableList<Task> observableList = FXCollections.observableArrayList(taskListIn);
        List<Task> listToday = new ArrayList<>();
        for (Task task : taskListIn){
            if(LocalDate.now().equals(task.getDateOfCreation())){
                listToday.add(task);
            }
        }
        observableList.addAll(listToday);

        ListView<Task> list = new ListView<>(observableList);
        list.setCellFactory(param -> new ListCell<>(){
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_mail.fxml"));
            Node node;
            ItemMailController listItemTaskController;
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
                    Task requestor = new Task();
                    requestor = task;

                    handlerLang.registerObserverLang(listItemTaskController);
                    handlerCompanyListener.registerObserverCompany(listItemTaskController);
                    listItemTaskController.updateLang(new DataLang(lang));
                    listItemTaskController.updateCompany(new DataCompany(company));

                    listItemTaskController.setLabelDate(task.getDateOfCreation());
                    listItemTaskController.setLabelTime(task.getTimeOfCreation());
                    listItemTaskController.setLabelCustomer(task.getCreator());
                    listItemTaskController.setLabelRoom(task.getRoom());
                    listItemTaskController.setTaskHTML(task.getExecutor());

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
