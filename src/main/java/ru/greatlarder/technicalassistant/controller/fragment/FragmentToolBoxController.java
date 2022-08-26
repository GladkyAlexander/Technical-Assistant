package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ru.greatlarder.technicalassistant.services.ProblemMonitor;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;

import java.util.Objects;

public class FragmentToolBoxController implements ObserverCompany {
    public ImageView imgEquipment;
    public ImageView imgTool;
    public ImageView imgIpAddress;
    public ImageView imgAllDefect;
    public ImageView imgAddEquipment;
    public ImageView imgAddTool;
    public ImageView imgAddDefect;
    public ImageView imgWatchWorkProg;
    public ImageView imgEngine;
    public TextField tfSerNum;
    public ImageView imgSearch;

    public void allEquipment(MouseEvent mouseEvent) {
    }

    public void allListTool(MouseEvent mouseEvent) {
    }

    public void allIpAddress(MouseEvent mouseEvent) {
    }

    public void allDefect(MouseEvent mouseEvent) {
    }

    public void addEquipment(MouseEvent mouseEvent) {
    }

    public void addTool(MouseEvent mouseEvent) {
    }

    public void addDefect(MouseEvent mouseEvent) {
    }

    public void watchWorkProjectors(MouseEvent mouseEvent) {
    }

    public void imgEnginSearch(MouseEvent mouseEvent) {
    }

    public void searchSerialNumber(MouseEvent mouseEvent) {
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        if(dataCompany.getCompany() != null){
            imgEquipment.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/all_equipment.png"))));
            imgAddEquipment.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_equipment.png"))));
            imgTool.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_button.png"))));
            imgAddTool.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_add.png"))));
            imgIpAddress.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_box_ip.png"))));
            imgAllDefect.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_box_defect.png"))));
            imgAddDefect.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/defect_add.png"))));
            imgWatchWorkProg.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_time.png"))));
            if (new ProblemMonitor().searchProblemMonitor(dataCompany.getCompany().getNameCompany())) {
                imgEngine.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/brakes_warning_light.png"))));
            } else
                imgEngine.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/brakes_ok_light.png"))));
        } else {
            imgEquipment.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/all_equipment_un_active.png"))));
            imgAddEquipment.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_equipment_un_active.png"))));
            imgTool.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_button_in_active.png"))));
            imgAddTool.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_add_in_active.png"))));
            imgIpAddress.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_box_ip_un_active.png"))));
            imgAllDefect.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_box_defect_un_active.png"))));
            imgAddDefect.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/defect_add_un_active.png"))));
            imgWatchWorkProg.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_time_un_active.png"))));
        }
    }
}
