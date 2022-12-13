package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import ru.greatlarder.technicalassistant.domain.ContactCart;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ItemPagePhoneBook implements ObserverLang, ObserverUser{
	
	@FXML public ListView<ContactCart> listIPHBone;
	@FXML public ImageView imgAddContact;
	@FXML public BorderPane borderPaneItemPhonePage;
	List<ContactCart> contactCartList;
	private String lang;
	private User user;
	HandlerLang handlerLang = GlobalLinkMainController.getMainController().handlerLang;
	@Override
	public void updateLang(DataLang dataLang) {
		this.lang = dataLang.getLanguage();
	}
	
	@Override
	public void updateUser(DataUser dataUser) {
		this.user = dataUser.getUser();
	}
	
	public void fragmentLoad(List<ContactCart> contactCartList){
		this.contactCartList = contactCartList;
		if(!this.contactCartList.isEmpty()){
			loadDataFragment();
		}
	}
	
	private void loadDataFragment() {
		listIPHBone.getItems().clear();
		
		listIPHBone.setItems(FXCollections.observableArrayList(contactCartList));
		
		listIPHBone.setCellFactory(param -> new ListCell<>(){
			final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_contact_cart.fxml"));
			
			Node node;
			ItemContactCart controller;
			
			{
				try {
					node = loader.load();
					controller = loader.getController();
					setPrefWidth(0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			protected void updateItem(ContactCart item, boolean empty) {
				super.updateItem(item, empty);
				if(empty){
					setGraphic(null);
				} else {
					handlerLang.registerObserverLang(loader.getController());
					controller.updateLang(new DataLang(lang));
					controller.updateUser(new DataUser(user));
					controller.loadFragment(item);
					setGraphic(node);
				}
			}
			
		});
		listIPHBone.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/ru/greatlarder/technicalassistant/css/table.css")).toExternalForm());
	}
	
	public void openPaneAddContact(MouseEvent mouseEvent) {
		borderPaneItemPhonePage.setRight(getNewContact());
	}
	private Node getNewContact(){
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_contact_cart.fxml"));
		Node node = null;
		try {
			node = loader.load();
			ItemContactCart controller = loader.getController();
			handlerLang.registerObserverLang(loader.getController());
			controller.updateLang(new DataLang(lang));
			controller.updateUser(new DataUser(user));
			controller.loadNewFragment(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return node;
	}
	
}
