package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import ru.greatlarder.technicalassistant.domain.PhoneBook;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewContactCard;
import ru.greatlarder.technicalassistant.services.list_view.impl.ListViewPhoneBook;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ItemPagePhoneBook implements Initializable {
	@FXML public Button btnOpenPaneAdd;
	@FXML public BorderPane borderPane;
	List<PhoneBook> contactCartList;
	private String lang;
	Language language = new LanguageImpl();
	private User user;
	public void fragmentLoad(List<PhoneBook> contactCartList){
		this.contactCartList = contactCartList;
		if(!this.contactCartList.isEmpty()){
			loadDataFragment();
		}
	}
	
	private void loadDataFragment() {
		GetListViewContactCard contacts = new ListViewPhoneBook();
		borderPane.setCenter(contacts.getListViewPhoneBook(contactCartList, this));
		}
	
	public void openPaneAddContact(ActionEvent mouseEvent) {
		borderPane.setRight(getNewContact());
	}
	private Node getNewContact(){
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_contact_cart.fxml"));
		Node node = null;
		try {
			node = loader.load();
			ItemContactCart controller = loader.getController();
			controller.loadNewFragment(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return node;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.user = GlobalLinkMainController.getMainController().getUser();
		this.lang = GlobalLinkMainController.getMainController().getLang();
		setLang(lang);
	}

	private void setLang(String lang) {
		btnOpenPaneAdd.setText(language.ADD(lang));
	}
}
