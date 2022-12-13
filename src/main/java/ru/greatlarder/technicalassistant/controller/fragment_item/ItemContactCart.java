package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.ContactCart;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.CartContactRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.CartContactRepositoryImpl;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

public class ItemContactCart implements ObserverLang, ObserverUser {
	@FXML public ImageView img;
	@FXML public Label labelLastName;
	@FXML public Label labelFirstName;
	@FXML public Label labelPhone;
	@FXML public Label labelMail;
	@FXML public Label lastName;
	@FXML public Label firstName;
	@FXML public Label phone;
	@FXML public Label mail;
	@FXML public TextField textFiledNewLastName;
	@FXML public TextField textFieldFirstName;
	@FXML public TextField textFieldPhone;
	@FXML public TextField textFieldEmail;
	@FXML public Button btnSaveNewContact;
	@FXML public GridPane gridPaneICC;
	private String lang;
	private User user;
	Language language = new LanguageImpl();
	private ContactCart contactCart;
	private ItemPagePhoneBook itemPagePhoneBook;
	
	@Override
	public void updateLang(DataLang dataLang) {
		this.lang = dataLang.getLanguage();
		setLanguage(this.lang);
	}
	
	private void setLanguage(String lang) {
		labelLastName.setText(language.LAST_NAME(lang));
		labelFirstName.setText(language.FIRST_NAME(lang));
		labelMail.setText(language.EMAIL(lang));
		labelPhone.setText(language.PHONE(lang));
	}
	
	@Override
	public void updateUser(DataUser dataUser) {
		this.user = dataUser.getUser();
	}
	public void loadFragment(ContactCart contact){
		this.contactCart = contact;
		loadUiPage(false);
		if(contactCart != null){
			loadDataPage();
		}
	}
	public void loadNewFragment(ItemPagePhoneBook itemPagePhoneBook){
		this.itemPagePhoneBook = itemPagePhoneBook;
		loadUiPage(true);
	}
	
	private void loadUiPage(boolean b) {
		
		textFiledNewLastName.setVisible(b);
		textFiledNewLastName.setManaged(b);
		textFieldFirstName.setVisible(b);
		textFieldFirstName.setManaged(b);
		textFieldEmail.setVisible(b);
		textFieldEmail.setManaged(b);
		textFieldPhone.setVisible(b);
		textFieldPhone.setManaged(b);
		btnSaveNewContact.setVisible(b);
		btnSaveNewContact.setManaged(b);
		boolean c = !b;
		lastName.setVisible(c);
		lastName.setManaged(c);
		firstName.setVisible(c);
		firstName.setManaged(c);
		mail.setVisible(c);
		mail.setManaged(c);
		phone.setVisible(c);
		phone.setManaged(c);
	}
	
	private void loadDataPage() {
		lastName.setText(contactCart.getLastName());
		firstName.setText(contactCart.getFirstName());
		phone.setText(contactCart.getPhone());
		mail.setText(contactCart.getMail());
		if(contactCart.getImgPath() != null && !contactCart.getImgPath().isEmpty()){
			img.setImage(new Image(contactCart.getImgPath()));
		}
	}
	
	public void saveContact(MouseEvent mouseEvent) {
		ContactCart contactCart1 = new ContactCart();
		contactCart1.setLastName(textFiledNewLastName.getText());
		contactCart1.setFirstName(textFieldFirstName.getText());
		contactCart1.setMail(textFieldEmail.getText());
		contactCart1.setPhone(textFieldPhone.getText());
		contactCart1.setImgPath(img.getImage().getUrl());
		contactCart1.setIdUser(user.getId());
		CartContactRepository contactRepository = new CartContactRepositoryImpl();
		if(contactRepository.setContactCart(contactCart1) != null){
			itemPagePhoneBook.borderPaneItemPhonePage.getChildren().remove(gridPaneICC);
		} else gridPaneICC.setStyle(StyleSRC.STYLE_DANGER);
	}
	

}
