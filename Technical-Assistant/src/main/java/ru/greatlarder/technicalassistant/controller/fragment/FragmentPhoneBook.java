package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemPagePhoneBook;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.PhoneBook;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListPhoneBook;
import ru.greatlarder.technicalassistant.services.database.sqlite.phone_book.GetListPhoneBookByUserSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class FragmentPhoneBook implements Initializable {
	
	 String lang;
	 Company company;
	 User user;
	@FXML public TabPane tablePaneFragmentPhoneBook;
	List<PhoneBook> listPhoneBo;

	public void loadFragment(){
		GetListPhoneBook getListPhoneBook = new GetListPhoneBookByUserSQLite();
		listPhoneBo = getListPhoneBook.getListPhoneBook(user, company.getNameCompany(), String.valueOf(user.getId()));
		if(lang != null) {
			for (String s : Objects.requireNonNull(getAlfabet(lang))){
				tablePaneFragmentPhoneBook.getTabs().add(loadTab(s));
			}
		}
	}
	
	private Tab loadTab(String name) {

		Tab tab = new Tab();
		tab.setText(name);
		List<PhoneBook> letterList = new ArrayList<>();
		for (PhoneBook p : listPhoneBo){
			if(String.valueOf(p.getLastName().charAt(0)).equals(name)){
				letterList.add(p);
			}
		}
		tab.setContent(getContentFoTab(letterList));
		return tab;
	}
	
	private Node getContentFoTab(List<PhoneBook> list) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_phone_book.fxml"));
		Node node = null;
		try {
			node = loader.load();
			ItemPagePhoneBook controller = loader.getController();
			controller.fragmentLoad(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return node;
	}
	private List<String> getAlfabet(String l){
		if (l.equals("Русский")) {
			List<String> ru = new ArrayList<>();
			for (char i = 'А'; i <= 'Я'; i++) {
				ru.add(String.valueOf(i));
			}
			return ru;
		}
		if(l.equals("English")){
			List<String> en = new ArrayList<>();
			for (char i = 'A'; i <= 'Z'; i++) {
				en.add(String.valueOf(i));
			}
			return en;
		}
		return null;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.user = GlobalLinkMainController.getMainController().getUser();
		this.company = GlobalLinkMainController.getMainController().getCompany();
		this.lang = GlobalLinkMainController.getMainController().getLang();
	}
}
