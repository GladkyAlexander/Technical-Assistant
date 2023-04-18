package ru.greatlarder.technicalassistant.services.global_link;

import ru.greatlarder.technicalassistant.controller.engineer.StartEngineerController;

public class GlobalLinkStartEngineerController {
    public static StartEngineerController startEngineerController;

    private GlobalLinkStartEngineerController(){

    }

    public static StartEngineerController getStartEngineerController(){
        return startEngineerController;
    }
    public static void setStartEngineerController(StartEngineerController startEngineerController){
        GlobalLinkStartEngineerController.startEngineerController = startEngineerController;
    }
}
