package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemCompanySelection implements Initializable {
    @FXML public ImageView imgLogoCompany;
    @FXML public Label labelNameCompany;
    @FXML public Label labelAddressCompany;
    @FXML public VBox vBox;
    private Company company;
    FileManager fileManager = new FileManagerImpl();

    public void setCompany(Company company) {
        this.company = company;
        loadUI();
    }
    private void loadUI(){
        labelNameCompany.setText(company.getNameCompany());
        labelAddressCompany.setText(company.getAddress());
        if(company.getLogoCompany() != null){
            imgLogoCompany.setImage(new Image(fileManager.getUrlFileImage(company.getLogoCompany())));
        }
    }

    public void loadCompany(MouseEvent mouseEvent) {
        GlobalLinkMainController.getMainController().updateCompany(new DataCompany(company));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(9.0);
        dropShadow.setOffsetY(9.0);
        dropShadow.setColor(Color.web("#37A742FF"));

        vBox.setEffect(dropShadow);
    }
}
