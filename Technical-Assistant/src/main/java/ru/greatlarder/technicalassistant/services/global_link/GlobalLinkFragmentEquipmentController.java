package ru.greatlarder.technicalassistant.services.global_link;

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
