package ru.greatlarder.technicalassistant.services;

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
