module ru.greatlarder.technicalassistant {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;

    opens ru.greatlarder.technicalassistant to javafx.fxml;
    exports ru.greatlarder.technicalassistant;
    exports ru.greatlarder.technicalassistant.controller;
    opens ru.greatlarder.technicalassistant.controller to javafx.fxml;
    exports ru.greatlarder.technicalassistant.controller.fragment_add;
    opens ru.greatlarder.technicalassistant.controller.fragment_add to javafx.fxml;
}