package ru.greatlarder.technicalassistant.controller.fragment;

import jakarta.mail.MessagingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import ru.greatlarder.technicalassistant.domain.Email;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.SetAffairs;
import ru.greatlarder.technicalassistant.services.database.sqlite.affaris.SetAffairsSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.mail.LoadTask;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FragmentWebTaskController implements Initializable {
    @FXML public BorderPane borderPane;
    public Button btnSave;
     User user;
     String lang;
    Language language = new LanguageImpl();
     Email email;
    
    public void load(Email email) throws MessagingException, IOException {

        this.email = email;
        WebView webView1 = new WebView();
        webView1.getEngine().loadContent(email.getTextHTML());
        borderPane.setCenter(webView1);

    }
    
    public void addAsATask() {
        LoadTask loadTask = new LoadTask();
        SetAffairs setAffairs = new SetAffairsSQLite();
        setAffairs.setAffairs(user, GlobalLinkMainController.getMainController().getCompany().getNameCompany()
            , loadTask.getTask(email.getTextHTML()));
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        setLanguage(lang);
    }
    private void setLanguage(String lang){
        btnSave.setText(language.ADD_AS_A_TASK(lang));
    }
}
