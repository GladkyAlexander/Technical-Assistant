package ru.greatlarder.technicalassistant.services.tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.domain.equipment.Projector;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository.EquipmentRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository.impl.EquipmentRepositoryImpl;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.tables.emp.WorkingHours;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.util.List;

public class ListProjectors implements ObserverCompany, ObserverLang, ObserverUser {
    private final List<Equipment> listProjector;
    private final TableView<WorkingHours> tableView = new TableView<>();
    public TableColumn<WorkingHours, String> listProjectors;
    public TableColumn<WorkingHours, String> workedTime;
    public TableColumn<WorkingHours, String> columnRoom;
    Language language = new LanguageImpl();
    private Company company;
    private String lang;
    EquipmentRepository equipmentRepository = new EquipmentRepositoryImpl();
    private User user;

    public ListProjectors(List<Equipment> listOfProjector) {
        this.listProjector = listOfProjector;
    }

    public TableView<WorkingHours> RemTable(){

        ObservableList<WorkingHours> observableList = FXCollections.observableArrayList();

        for (Equipment equipment : listProjector){
            WorkingHours h = new WorkingHours((Projector) equipment);
            observableList.addAll(h);
        }
        tableView.setEditable(true);

        listProjectors = new TableColumn<>(language.PROJECTOR(lang));
        listProjectors.setCellValueFactory(data-> data.getValue().serialNumberProperty());

        workedTime = new TableColumn<>(language.OPERATING_HOURS(lang));
        workedTime.setCellValueFactory(data-> data.getValue().hoursProperty());
        workedTime.setCellFactory(TextFieldTableCell.forTableColumn());
        workedTime.setOnEditCommit(
                workingHoursStringCellEditEvent -> {
                    (workingHoursStringCellEditEvent.getTableView().getItems().get(workingHoursStringCellEditEvent
                            .getTablePosition().getRow())).setHours(workingHoursStringCellEditEvent.getNewValue());

                    equipmentRepository.change(
                            equipmentRepository.getEquipmentBySerialNumber(company.getNameCompany()
                                    , workingHoursStringCellEditEvent.getTableView().getSelectionModel().getSelectedItem().getSerialNumber()).getId()
                            , "timeWorkLampProjector"
                            , workingHoursStringCellEditEvent.getNewValue()
                    );
                }
        );

        columnRoom = new TableColumn<>(language.ROOM(lang));
        columnRoom.setCellValueFactory(data-> data.getValue().roomProperty());

        tableView.getColumns().setAll(listProjectors, workedTime, columnRoom);

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
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }

    private void setLanguage(String lang) {

    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
    }
}
