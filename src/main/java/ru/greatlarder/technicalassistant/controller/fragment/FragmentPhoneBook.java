package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemPagePhoneBook;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.ContactCart;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.CartContactRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.CartContactRepositoryImpl;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentPhoneBook implements ObserverLang, ObserverCompany, ObserverUser {
	
	private String lang;
	private Company company;
	private User user;
	@FXML public TabPane tablePaneFragmentPhoneBook;
	
	@Override
	public void updateCompany(DataCompany dataCompany) {
		if(dataCompany == null){
			this.company = null;
		} else this.company = dataCompany.getCompany();
	}
	
	@Override
	public void updateLang(DataLang dataLang) {
		this.lang = dataLang.getLanguage();
		tablePaneFragmentPhoneBook.getTabs().clear();
	}
	
	@Override
	public void updateUser(DataUser dataUser) {
		if(dataUser == null){
			this.user = null;
		} else this.user = dataUser.getUser();
	}
	
	public void loadFragment(){
		
		if(lang != null) {
			for (String s : Objects.requireNonNull(getAlfabet(lang))){
				tablePaneFragmentPhoneBook.getTabs().add(loadTab(s));
			}
		}
	}
	
	private Tab loadTab(String name) {
		CartContactRepository contactRepository = new CartContactRepositoryImpl();
		Tab tab = new Tab();
		tab.setText(name);
		tab.setContent(getContentFoTab(contactRepository.getContactCardByUserByLetter(user.getId(), name)));
		return tab;
	}
	
	private Node getContentFoTab(List<ContactCart> list) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_phone_book.fxml"));
		Node node = null;
		try {
			node = loader.load();
			ItemPagePhoneBook controller = loader.getController();
			controller.updateLang(new DataLang(lang));
			controller.updateUser(new DataUser(user));
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
	
}
