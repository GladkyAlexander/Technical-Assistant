package ru.greatlarder.technicalassistant.controller.fragment;

import jakarta.mail.MessagingException;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import ru.greatlarder.technicalassistant.domain.Email;

import java.io.IOException;

public class FragmentWebTaskController {
    @FXML public WebView webView;

    public void load(Email email) throws MessagingException, IOException {

            this.webView.getEngine().loadContent(email.getTextHTML());

    }

}
