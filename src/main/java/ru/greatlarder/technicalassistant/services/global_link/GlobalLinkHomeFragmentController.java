package ru.greatlarder.technicalassistant.services.global_link;

import ru.greatlarder.technicalassistant.controller.reception.HomeReceptionController;

public class GlobalLinkHomeFragmentController {
    public static HomeReceptionController homeReceptionController;

    private GlobalLinkHomeFragmentController(){
    }

    public static HomeReceptionController getHomeReceptionController(){
        return homeReceptionController;
    }
    public static void setHomeReceptionController(HomeReceptionController homeReceptionController){
        GlobalLinkHomeFragmentController.homeReceptionController = homeReceptionController;
    }
}
