package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentAddEquipmentController;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.services.ProblemMonitor;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

import java.io.IOException;
import java.util.Objects;

public class FragmentToolBoxController implements ObserverCompany, ObserverLang {
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
    private Company company;
    private String lang;
    Language language = new LanguageImpl();
    HandlerCompanyListener handlerCompanyListener = new HandlerCompanyListener();
    HandlerLang handlerLang = new HandlerLang();

    public void allEquipment(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEquipment.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerCompanyListener.registerObserverCompany(loader.getController());
            handlerLang.onNewDataLang(new DataLang(lang));
            FragmentEquipmentController controller = loader.getController();
            controller.loadFragment(company);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void allListTool(MouseEvent mouseEvent) {
    }

    public void allIpAddress(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentIpAddress.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerCompanyListener.registerObserverCompany(loader.getController());
            handlerLang.onNewDataLang(new DataLang(lang));
            FragmentIpAddressController controller = loader.getController();
            controller.loadFragment(company);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void allDefect(MouseEvent mouseEvent) {
    }

    public void addEquipment(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_equipment.fxml"));
        try {
            if (company != null) {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setRight(loader.load());
                handlerLang.registerObserverLang(loader.getController());
                handlerCompanyListener.registerObserverCompany(loader.getController());
                handlerLang.onNewDataLang(new DataLang(lang));
                handlerCompanyListener.onNewDataCompany(new DataCompany(company));
                FragmentAddEquipmentController fragmentEquipmentController = loader.getController();
                fragmentEquipmentController.loadFragment();

            } else {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(new Label(language.FILL_IN_THE_DB(lang)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        this.company = dataCompany.getCompany();
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
        handlerCompanyListener.onNewDataCompany(new DataCompany(dataCompany.getCompany()));
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        handlerLang.onNewDataLang(new DataLang(dataLang.getLanguage()));
    }
}
