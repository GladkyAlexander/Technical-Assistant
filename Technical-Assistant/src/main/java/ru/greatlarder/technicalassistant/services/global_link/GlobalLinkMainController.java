package ru.greatlarder.technicalassistant.services.global_link;

import ru.greatlarder.technicalassistant.controller.MainController;

public class GlobalLinkMainController {
    public static MainController mainController;

    private GlobalLinkMainController(){

    }

    public static MainController getMainController(){
        return mainController;
    }
    public static void setMainController(MainController mainController){
        GlobalLinkMainController.mainController = mainController;
    }
}
