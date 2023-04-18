module ru.greatlarder.technicalassistant {
    requires javafx.controls;
    requires javafx.fxml;
    
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.commons.io;
    requires java.datatransfer;
    requires java.desktop;
    requires javafx.swing;
    requires org.jsoup;
    requires javafx.web;
    requires jfxtras.controls;
    requires java.sql;
    requires org.apache.commons.net;
    requires jakarta.mail;
    requires org.apache.poi.poi;
    
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
    exports ru.greatlarder.technicalassistant.services;
    opens ru.greatlarder.technicalassistant.services to javafx.fxml;
    exports ru.greatlarder.technicalassistant.services.net;
    opens ru.greatlarder.technicalassistant.services.net to javafx.fxml;
    exports ru.greatlarder.technicalassistant.services.manager;
    opens ru.greatlarder.technicalassistant.services.manager to javafx.fxml;
    exports ru.greatlarder.technicalassistant.services.list_view.impl;
    opens ru.greatlarder.technicalassistant.services.list_view.impl to javafx.fxml;
    exports ru.greatlarder.technicalassistant.services.list_view;
    opens ru.greatlarder.technicalassistant.services.list_view to javafx.fxml;

}