package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.PhoneBook;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.SetPhoneBook;
import ru.greatlarder.technicalassistant.services.database.UpdatePhoneBook;
import ru.greatlarder.technicalassistant.services.database.sqlite.phone_book.SetPhoneBookSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.phone_book.UpdatePhoneBookSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemContactCart implements Initializable {
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
	@FXML public Button btnChange;
	String lang;
	User user;
	Language language = new LanguageImpl();
	PhoneBook contactCart;
	ItemPagePhoneBook itemPagePhoneBook;
	Company company;

	boolean flag = false;

	private void setLanguage(String lang) {
		labelLastName.setText(language.LAST_NAME(lang));
		labelFirstName.setText(language.FIRST_NAME(lang));
		labelMail.setText(language.EMAIL(lang));
		labelPhone.setText(language.PHONE(lang));
		btnSaveNewContact.setText(language.SAVE(lang));
		btnChange.setText(language.CHANGE_DATA(lang));
	}

	public void loadFragment(PhoneBook contact, ItemPagePhoneBook itemPagePhoneBook){
		this.contactCart = contact;
		this.itemPagePhoneBook = itemPagePhoneBook;
		loadUiPage(false);
		if(contactCart != null){
			loadDataPage();
		}
	}
	public void loadNewFragment(ItemPagePhoneBook itemPagePhoneBook){
		this.itemPagePhoneBook = itemPagePhoneBook;
		loadUiPage(true);
	}
	public void loadChangeContact(PhoneBook contact, ItemPagePhoneBook itemPagePhoneBook){
		this.contactCart = contact;
		this.itemPagePhoneBook = itemPagePhoneBook;
		flag = true;
		loadUiPage(true);
		textFiledNewLastName.setPromptText(contact.getLastName());
		textFieldFirstName.setPromptText(contact.getFirstName());
		textFieldEmail.setPromptText(contact.getMail());
		textFieldPhone.setPromptText(contact.getPhone());
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

		btnChange.setVisible(c);
		btnChange.setManaged(c);
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
		if(flag) {
			contactCart.setLastName(textFiledNewLastName.getText());
			contactCart.setFirstName(textFieldFirstName.getText());
			contactCart.setMail(textFieldEmail.getText());
			contactCart.setPhone(textFieldPhone.getText());
			contactCart.setImgPath(img.getImage().getUrl());
			contactCart.setIdUser(user.getId());
			UpdatePhoneBook updatePhoneBook = new UpdatePhoneBookSQLite();
			updatePhoneBook.updatePhoneBook(user, company.getNameCompany(), contactCart, contactCart.getId());
			itemPagePhoneBook.borderPane.getChildren().remove(gridPaneICC);
		} else {
			PhoneBook contactCart1 = new PhoneBook();
			contactCart1.setLastName(textFiledNewLastName.getText());
			contactCart1.setFirstName(textFieldFirstName.getText());
			contactCart1.setMail(textFieldEmail.getText());
			contactCart1.setPhone(textFieldPhone.getText());
			contactCart1.setImgPath(img.getImage().getUrl());
			contactCart1.setIdUser(user.getId());
			SetPhoneBook setPhoneBook = new SetPhoneBookSQLite();
			if (setPhoneBook.setPhoneBook(user, company.getNameCompany(), contactCart1) != null) {
				itemPagePhoneBook.borderPane.getChildren().remove(gridPaneICC);
			} else gridPaneICC.setStyle(StyleSRC.STYLE_DANGER);
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.user = GlobalLinkMainController.getMainController().getUser();
		this.company = GlobalLinkMainController.getMainController().getCompany();
		this.lang = GlobalLinkMainController.getMainController().getLang();
		setLanguage(lang);
	}

	public void changeContact(ActionEvent actionEvent) {
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_contact_cart.fxml"));
		try {
			itemPagePhoneBook.borderPane.setRight(loader.load());
			ItemContactCart controller = loader.getController();
			controller.loadChangeContact(contactCart,itemPagePhoneBook);
			btnChange.setVisible(false);
			btnChange.setManaged(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
