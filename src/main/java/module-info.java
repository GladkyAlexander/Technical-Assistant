module ru.greatlarder.technicalassistant {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires javafx.swing;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires org.apache.commons.io;
    requires javafx.web;
    requires org.jsoup;
    requires java.mail;
    requires org.xerial.sqlitejdbc;
    requires jdk.jsobject;
    requires jsch;
    requires mysql.connector.java;

    opens ru.greatlarder.technicalassistant to javafx.fxml;
    exports ru.greatlarder.technicalassistant;
    exports ru.greatlarder.technicalassistant.controller;
    opens ru.greatlarder.technicalassistant.controller to javafx.fxml;
    exports ru.greatlarder.technicalassistant.controller.fragment_add;
    opens ru.greatlarder.technicalassistant.controller.fragment_add to javafx.fxml;
    exports ru.greatlarder.technicalassistant.controller.engineer;
    opens ru.greatlarder.technicalassistant.controller.engineer to javafx.fxml;
    exports ru.greatlarder.technicalassistant.controller.fragment;
    opens ru.greatlarder.technicalassistant.controller.fragment to javafx.fxml;
    exports ru.greatlarder.technicalassistant.controller.fragment_item;
    opens ru.greatlarder.technicalassistant.controller.fragment_item to javafx.fxml;
    exports ru.greatlarder.technicalassistant.controller.reception;
    opens ru.greatlarder.technicalassistant.controller.reception to javafx.fxml;

}