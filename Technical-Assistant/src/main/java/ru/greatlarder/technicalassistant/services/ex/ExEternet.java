package ru.greatlarder.technicalassistant.services.ex;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import ru.greatlarder.technicalassistant.controller.fragment_item.UpsController;

import java.io.IOException;

public class ExEternet {
    public Node start(String england, String message) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/ups.fxml"));
            Node node = loader.load();
            UpsController controller = loader.getController();
            controller.updateLang(england);
            controller.loadFragmentUpsController(message);
        return node;
    }
}
