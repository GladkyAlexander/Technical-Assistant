package ru.great_larder.technical_assistant;

public class GlobalLinkMainController {
    public static MainActivity mainActivity;

    private GlobalLinkMainController(){

    }

    public static MainActivity getMainActivity(){
        return mainActivity;
    }
    public static void setMainActivity(MainActivity mainController){
        GlobalLinkMainController.mainActivity = mainController;
    }
}
