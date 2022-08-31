package ru.greatlarder.technicalassistant.services;

import ru.greatlarder.technicalassistant.controller.fragment.FragmentEquipmentController;

public class GlobalLinkFragmentEquipmentController {
    public static FragmentEquipmentController fragmentEquipmentController;

    private GlobalLinkFragmentEquipmentController(){
    }

    public static FragmentEquipmentController getFragmentEquipmentController(){
        return fragmentEquipmentController;
    }
    public static void setFragmentEquipmentController(FragmentEquipmentController fragmentEquipmentController){
        GlobalLinkFragmentEquipmentController.fragmentEquipmentController = fragmentEquipmentController;
    }
}
