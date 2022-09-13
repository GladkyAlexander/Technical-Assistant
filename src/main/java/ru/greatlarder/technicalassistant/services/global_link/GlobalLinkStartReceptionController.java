package ru.greatlarder.technicalassistant.services.global_link;

import ru.greatlarder.technicalassistant.controller.reception.StartReceptionController;

public class GlobalLinkStartReceptionController {
    public static StartReceptionController startReceptionController;

    private GlobalLinkStartReceptionController(){

    }

    public static StartReceptionController getStartReceptionController(){
        return startReceptionController;
    }
    public static void setStartReceptionController(StartReceptionController startReceptionController){
        GlobalLinkStartReceptionController.startReceptionController = startReceptionController;
    }
}
