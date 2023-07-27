package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.ListEquipmentByNameSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.LanguageNameEquipment;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageNameEquipmentImpl;
import ru.greatlarder.technicalassistant.services.tables.TableViewProjectors;

import java.net.URL;
import java.util.ResourceBundle;

public class FragmentAddWatchWorkProjectorsController implements Initializable {

	@FXML public VBox vBoxTime;
	@FXML public ImageView close;
	Company company;
	String lang;
	User user;
	LanguageNameEquipment languageNameEquipment = new LanguageNameEquipmentImpl();

	public void loadFragment(){
		GetListEquipment getListEquipment = new ListEquipmentByNameSQLite();
		TableViewProjectors listProjectors = new TableViewProjectors(getListEquipment.getListEquipment(user, company.getNameCompany()
			, languageNameEquipment.getProjector(lang)));
		vBoxTime.getChildren().add(listProjectors.RemTable());
	}

	public void close() {
		((BorderPane) vBoxTime.getParent()).getChildren().remove(vBoxTime);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.user = GlobalLinkMainController.getMainController().getUser();
		this.company = GlobalLinkMainController.getMainController().getCompany();
		this.lang = GlobalLinkMainController.getMainController().getLang();
		loadFragment();
	}
}
