package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.repository.UserRepository;
import ru.greatlarder.technicalassistant.repository.impl.UserRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

public class FragmentRegistrationUserController implements ObserverLang{
    @FXML
    public Label labelLastName;
    @FXML
    public Label labelFirstName;
    @FXML
    public Label labelEmailAddress;
    @FXML
    public TextField tfLastName;
    @FXML
    public TextField tfFirstName;
    @FXML
    public TextField tfMailAddress;
    @FXML
    public Button btnSave;
    @FXML
    public GridPane gridPaneAdd;
    @FXML
    public Label labelPhone;
    @FXML
    public TextField tfPhone;
    @FXML
    public MenuButton menuButtonLanguage;
    @FXML
    public MenuItem menuItemRu;
    @FXML
    public MenuItem menuItemEn;
    @FXML
    public ComboBox<String> comboBoxPost;
    @FXML public Label labelRegister;
    Language lines = new LanguageImpl();
    String lang;

    public void saveUser(ActionEvent actionEvent) {
        UserRepository userRepository = new UserRepositoryImpl();
        User user = new User();
        user.setLastName(tfLastName.getText());
        user.setFirstName(tfFirstName.getText());
        user.setMailAddress(tfMailAddress.getText());
        user.setPhone(tfPhone.getText());
        user.setPost(comboBoxPost.getValue());
        if(menuButtonLanguage.getText().equals("Россия")){
            user.setLanguage("Русский");
        }
        if(menuButtonLanguage.getText().equals("England")){
            user.setLanguage("English");
        }
        userRepository.setUser(user);

        if (userRepository.getUser().getFirstName().equals(user.getFirstName()) && userRepository.getUser()
                .getLastName().equals(user.getLastName())) {
            gridPaneAdd.setStyle(StyleSRC.STYLE_EXCELLENT);
            GlobalLinkMainController.getMainController().updateUser(userRepository.getUser());
        } else {
            gridPaneAdd.setStyle(StyleSRC.STYLE_DANGER);
        }
    }

    public void loadPage() {
        gridPaneAdd.setStyle(StyleSRC.STYLE_ORDINARY);
        comboBoxPost.setItems(FXCollections.observableArrayList(lines.LIST_POST(lang)));
    }

    public void setMenuItemRu(ActionEvent actionEvent) {
        menuButtonLanguage.setText(menuItemRu.getText());
    }

    public void setMenuItemEn(ActionEvent actionEvent) {
        menuButtonLanguage.setText(menuItemEn.getText());
    }

    public void setLanguage(String lange) {
        labelRegister.setText(lines.REGISTRY(lange));
        this.labelLastName.setText(lines.LAST_NAME(lange));
        labelFirstName.setText(lines.FIRST_NAME(lange));
        labelEmailAddress.setText(lines.EMAIL(lange));
        labelPhone.setText(lines.PHONE(lange));
        comboBoxPost.setPromptText(lines.POST(lange));
        menuButtonLanguage.setText(lines.SELECT_A_COUNTRY(lange));
        btnSave.setText(lines.SAVE(lange));
        comboBoxPost.setItems(FXCollections.observableArrayList(lines.LIST_POST(lange)));
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(dataLang.getLanguage());
    }
}
