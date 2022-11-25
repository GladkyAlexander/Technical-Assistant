package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ru.greatlarder.technicalassistant.controller.fragment_add.*;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository.impl.EquipmentRepositoryImpl;
import ru.greatlarder.technicalassistant.services.ProblemMonitor;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.HandlerUserListener;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.IOException;
import java.util.Objects;

public class FragmentToolBoxController implements ObserverCompany, ObserverLang, ObserverUser {
    @FXML public ImageView imgEquipment;
    @FXML public ImageView imgTool;
    @FXML public ImageView imgIpAddress;
    @FXML public ImageView imgAllDefect;
    @FXML public ImageView imgAddEquipment;
    @FXML public ImageView imgAddTool;
    @FXML public ImageView imgAddDefect;
    @FXML public ImageView imgWatchWorkProg;
    @FXML public ImageView imgEngine;
    @FXML public TextField tfSerNum;
    @FXML public ImageView imgSearch;
    public ImageView imgRooms;
    public ImageView imgAllEvent;
    public ImageView imgAddRoom;
    public ImageView imgAddEvent;
    private Company company;
    private String lang;
    Language language = new LanguageImpl();
    HandlerCompanyListener handlerCompanyListener = GlobalLinkStartEngineerController.getStartEngineerController().handlerCompanyListener;
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().handlerLang;
    HandlerUserListener handlerUserListener = GlobalLinkMainController.getMainController().handlerUserListener;
    private User user;

    public void allEquipment(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEquipment.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerCompanyListener.registerObserverCompany(loader.getController());
            handlerUserListener.registerObserverUser(loader.getController());
            FragmentEquipmentController controller = loader.getController();
            controller.updateLang(new DataLang(this.lang));
            controller.updateUser(new DataUser(this.user));
            controller.updateCompany(new DataCompany(this.company));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void allTool(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentTool.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerCompanyListener.registerObserverCompany(loader.getController());
            handlerUserListener.registerObserverUser(loader.getController());
            FragmentToolController controller = loader.getController();
            controller.updateLang(new DataLang(this.lang));
            controller.updateUser(new DataUser(this.user));
            controller.updateCompany(new DataCompany(this.company));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void allIpAddress(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentIpAddress.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerCompanyListener.registerObserverCompany(loader.getController());
            handlerUserListener.registerObserverUser(loader.getController());
            FragmentIpAddressController controller = loader.getController();
            controller.updateLang(new DataLang(this.lang));
            controller.updateUser(new DataUser(this.user));
            controller.updateCompany(new DataCompany(this.company));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void allDefect(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentDefect.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerCompanyListener.registerObserverCompany(loader.getController());
            handlerUserListener.registerObserverUser(loader.getController());
            FragmentDefectController controller = loader.getController();
            controller.updateLang(new DataLang(this.lang));
            controller.updateUser(new DataUser(this.user));
            controller.updateCompany(new DataCompany(this.company));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEquipment(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_equipment.fxml"));
        try {
            if (company != null) {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setRight(loader.load());
                handlerLang.registerObserverLang(loader.getController());
                handlerCompanyListener.registerObserverCompany(loader.getController());
                handlerUserListener.registerObserverUser(loader.getController());
                FragmentAddEquipmentController fragmentEquipmentController = loader.getController();
                fragmentEquipmentController.updateLang(new DataLang(this.lang));
                fragmentEquipmentController.updateUser(new DataUser(this.user));
                fragmentEquipmentController.updateCompany(new DataCompany(this.company));
            } else {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(new Label(language.FILL_IN_THE_DB(lang)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTool(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_tool.fxml"));
        try {
            if (company != null) {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setRight(loader.load());
                handlerLang.registerObserverLang(loader.getController());
                handlerCompanyListener.registerObserverCompany(loader.getController());
                handlerUserListener.registerObserverUser(loader.getController());
                FragmentAddToolController controller = loader.getController();
                controller.updateLang(new DataLang(this.lang));
                controller.updateUser(new DataUser(this.user));
                controller.updateCompany(new DataCompany(this.company));
            } else {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(new Label(language.FILL_IN_THE_DB(lang)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDefect(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_defect.fxml"));
        try {
            if (company != null) {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setRight(loader.load());
                handlerLang.registerObserverLang(loader.getController());
                handlerCompanyListener.registerObserverCompany(loader.getController());
                handlerUserListener.registerObserverUser(loader.getController());
                FragmentAddDefectController controller = loader.getController();
                controller.updateLang(new DataLang(this.lang));
                controller.updateUser(new DataUser(this.user));
                controller.updateCompany(new DataCompany(this.company));
            } else {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(new Label(language.FILL_IN_THE_DB(lang)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void watchWorkProjectors(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_watch_work_projectors.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setRight(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerCompanyListener.registerObserverCompany(loader.getController());
            FragmentAddWatchWorkProjectorsController controller = loader.getController();
            controller.updateLang(new DataLang(this.lang));
            controller.updateUser(new DataUser(this.user));
            controller.updateCompany(new DataCompany(this.company));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void imgEnginSearch(MouseEvent mouseEvent) {
        if(new ProblemMonitor().searchProblemMonitor(company.getNameCompany())) {
            allDefect(mouseEvent);
        }
    }

    public void searchSerialNumber(MouseEvent mouseEvent) {
        int length = tfSerNum.getLength();

        FragmentEquipmentOneController equipmentItemController;

        if (length > 0) {
            Equipment equipment = new EquipmentRepositoryImpl().getEquipmentBySerialNumber(company.getNameCompany(), tfSerNum.getText());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEquipmentOne.fxml"));

            if (equipment != null) {
                try {

                    Scene scene = new Scene(loader.load());
                    Stage stage = new Stage();

                    handlerLang.registerObserverLang(loader.getController());
                    handlerUserListener.registerObserverUser(loader.getController());
                    handlerCompanyListener.registerObserverCompany(loader.getController());

                    equipmentItemController = loader.getController();
                    equipmentItemController.updateLang(new DataLang(lang));
                    equipmentItemController.updateUser(new DataUser(user));
                    equipmentItemController.updateCompany(new DataCompany(company));
                    equipmentItemController.setEquip(equipment);

                    stage.setTitle(equipment.getModel() + " : " + equipment.getSerialNumber());
                    stage.setScene(scene);
                    stage.show();
                    tfSerNum.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(new Label(language.NO_DEVICE_WITH_THIS_SERIAL_NUMBER(lang)));
                tfSerNum.clear();
            }
        } else {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(new Label(language.ENTER_THE_SERIAL_NUMBER(lang)));
            tfSerNum.clear();
        }
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
        if (company != null) {
            imgEquipment.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/all_equipment.png"))));
            imgAddEquipment.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_equipment.png"))));
            imgTool.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_button.png"))));
            imgAddTool.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_add.png"))));
            imgIpAddress.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_box_ip.png"))));
            imgAllDefect.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_box_defect.png"))));
            imgAddDefect.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/defect_add.png"))));
            imgWatchWorkProg.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_time.png"))));
            imgRooms.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/all_room_active.png"))));
            imgAllEvent.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/all_event_active.png"))));
            imgAddRoom.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_rooms_active.png"))));
            imgAddEvent.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_events_active.png"))));

            if (new ProblemMonitor().searchProblemMonitor(company.getNameCompany())) {
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
            imgRooms.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/all_room_unactive.png"))));
            imgAllEvent.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/all_events_un_active.png"))));
            imgAddRoom.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_rooms_un_active.png"))));
            imgAddEvent.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_events_un_active.png"))));
        }
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
    }

    public void allRooms(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentRoom.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerUserListener.registerObserverUser(loader.getController());
            handlerCompanyListener.registerObserverCompany(loader.getController());
            FragmentRoom controller = loader.getController();

            controller.updateLang(new DataLang(this.lang));
            controller.updateUser(new DataUser(this.user));
            controller.updateCompany(new DataCompany(this.company));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openAllEvent(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEvents.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerUserListener.registerObserverUser(loader.getController());
            handlerCompanyListener.registerObserverCompany(loader.getController());
            FragmentEventController controller = loader.getController();

            controller.updateLang(new DataLang(this.lang));
            controller.updateUser(new DataUser(this.user));
            controller.updateCompany(new DataCompany(this.company));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRoom(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_company.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerCompanyListener.registerObserverCompany(loader.getController());
            handlerUserListener.registerObserverUser(loader.getController());

            if(company != null) {
                FragmentAddCompanyController fragmentAddCompanyController = loader.getController();

                fragmentAddCompanyController.updateLang(new DataLang(this.lang));
                fragmentAddCompanyController.updateUser(new DataUser(this.user));

                fragmentAddCompanyController.loadChangeCompanyFragment(this.company);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEvent(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_company.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerUserListener.registerObserverUser(loader.getController());
            handlerCompanyListener.registerObserverCompany(loader.getController());

            if(company != null) {
                FragmentAddCompanyController fragmentAddCompanyController = loader.getController();

                fragmentAddCompanyController.updateLang(new DataLang(this.lang));
                fragmentAddCompanyController.updateUser(new DataUser(this.user));

                fragmentAddCompanyController.loadChangeCompanyFragment(this.company);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
