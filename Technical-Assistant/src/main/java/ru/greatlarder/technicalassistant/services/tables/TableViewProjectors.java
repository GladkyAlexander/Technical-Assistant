package ru.greatlarder.technicalassistant.services.tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.equipment.Projector;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetEquipment;
import ru.greatlarder.technicalassistant.services.database.UpdateEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.EquipmentBySerialNumberSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.UpdateEquipmentSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.LanguageNameEquipment;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageNameEquipmentImpl;
import ru.greatlarder.technicalassistant.services.tables.emp.WorkingHours;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TableViewProjectors implements Initializable {
    private final List<Equipment> listProjector;
    private final TableView<WorkingHours> tableView = new TableView<>();
    public TableColumn<WorkingHours, String> listProjectors;
    public TableColumn<WorkingHours, String> workedTime;
    public TableColumn<WorkingHours, String> columnRoom;
    Language language = new LanguageImpl();
    LanguageNameEquipment languageNameEquipment = new LanguageNameEquipmentImpl();
    Company company;
    String lang = GlobalLinkMainController.getMainController().getLang();
    User user;

    public TableViewProjectors(List<Equipment> listOfProjector) {
        this.listProjector = listOfProjector;
    }

    public TableView<WorkingHours> RemTable(){

        ObservableList<WorkingHours> observableList = FXCollections.observableArrayList();

        for (Equipment equipment : listProjector){
            WorkingHours h = new WorkingHours((Projector) equipment);
            observableList.addAll(h);
        }
        tableView.setEditable(true);

        listProjectors = new TableColumn<>(languageNameEquipment.getProjector(lang));
        listProjectors.setCellValueFactory(data-> data.getValue().serialNumberProperty());

        workedTime = new TableColumn<>(language.OPERATING_HOURS(lang));
        workedTime.setCellValueFactory(data-> data.getValue().hoursProperty());
        workedTime.setCellFactory(TextFieldTableCell.forTableColumn());
        workedTime.setOnEditCommit(
                workingHoursStringCellEditEvent -> {
                    (workingHoursStringCellEditEvent.getTableView().getItems().get(workingHoursStringCellEditEvent
                            .getTablePosition().getRow())).setHours(workingHoursStringCellEditEvent.getNewValue());
                    UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
                    GetEquipment getEquipment = new EquipmentBySerialNumberSQLite();
                    updateEquipment.updateEquipment(user, company, getEquipment.getEquipment(user
                            , company.getNameCompany()
                            , workingHoursStringCellEditEvent.getTableView().getSelectionModel().getSelectedItem().getSerialNumber()));
                }
        );

        columnRoom = new TableColumn<>(language.ROOM(lang));
        columnRoom.setCellValueFactory(data-> data.getValue().roomProperty());

        tableView.getColumns().setAll(columnRoom, listProjectors, workedTime);

        serviceColumn(listProjectors);
        tableView.setItems(observableList);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefSize(Region.USE_COMPUTED_SIZE,1000);

        return tableView;
    }

    private void serviceColumn(TableColumn<WorkingHours, String> column) {
        column.setCellFactory(tc -> {
            TableCell<WorkingHours, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(column.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
    }
}
