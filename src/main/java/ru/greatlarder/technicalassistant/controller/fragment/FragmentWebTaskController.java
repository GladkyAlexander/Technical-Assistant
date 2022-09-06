package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class FragmentWebTaskController {
    @FXML public WebView webView;

    public void load(String url){
        this.webView.getEngine().loadContent(url);
    }

}
