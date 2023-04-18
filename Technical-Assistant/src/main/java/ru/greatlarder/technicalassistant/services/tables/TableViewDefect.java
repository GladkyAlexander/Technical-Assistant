package ru.greatlarder.technicalassistant.services.tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemDefectController;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Defect;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class TableViewDefect implements Initializable {
    Company company;
    String lang;
    Language language = new LanguageImpl();
    User user;

    public ListView<Defect> upBox(List<Defect> defectList){
        ObservableList<Defect> observableList = FXCollections.observableArrayList();
        observableList.addAll(defectList);
        ListView<Defect> list = new ListView<>(observableList);

        list.setCellFactory(param -> new ListCell<>(){
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_defect.fxml"));
            Node node;
            ItemDefectController itemDefectController;
            {
                try {
                    node = loader.load();
                    itemDefectController = loader.getController();
                    setPrefWidth(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void updateItem(Defect item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                } else {
                    Defect defect;
                    defect = item;
                    itemDefectController.setDefect(defect);
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy" + " г.");
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

                    if(item.getCondition().equals(language.SATISFACTORY(lang))){
                        itemDefectController.hBoxItemDefect.setStyle(StyleSRC.STYLE_EXCELLENT);
                    }
                    itemDefectController.setImgLogoEquipment("/ru/greatlarder/technicalassistant/images/warning_min.png");

                    itemDefectController.setLabelDateOfCreation(item.getDate_defect().format(dateFormat));
                    itemDefectController.setLabelTimeOfCreation(item.getTime_defect().format(dateTimeFormatter));
                    itemDefectController.setLabelSerialNumber(item.getSerial_number_equipment());
                    itemDefectController.setTextAreaCauseMalfunction(item.getCauseOfTheMalfunction());
                    itemDefectController.setTextAreaHowFixed(item.getHowFixed());
                    itemDefectController.setTextAreaNote(item.getNoteExecutor());
                    itemDefectController.setCheckBoxStatus(item.getCondition());
                    itemDefectController.setIdEquipment(item.getIdEquipment());

                    itemDefectController.textAreaCauseMalfunction.setWrapText(true);
                    itemDefectController.textAreaHowFixed.setWrapText(true);
                    itemDefectController.textAreaNote.setWrapText(true);
                    setGraphic(node);
                }
            }
        });
        return list;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
    }
}
