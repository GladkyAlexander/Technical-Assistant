module ru.greatlarder.technicalassistant {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens ru.greatlarder.technicalassistant to javafx.fxml;
    exports ru.greatlarder.technicalassistant;
}