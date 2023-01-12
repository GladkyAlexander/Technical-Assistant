package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.EquipmentRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.EquipmentRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.tables.ListProjectors;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.HandlerUserListener;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

public class FragmentAddWatchWorkProjectorsController implements ObserverLang, ObserverCompany, ObserverUser {

	@FXML public VBox vBoxTime;
	@FXML public ImageView close;
	EquipmentRepository equipmentRepository = new EquipmentRepositoryImpl();
	ListProjectors listProjectors;
	private Company company;
	private String lang;
	private User user;
	Language language = new LanguageImpl();
	HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
	HandlerCompanyListener handlerCompanyListener = GlobalLinkMainController.mainController.getHandlerCompanyListener();
	HandlerUserListener handlerUserListener = GlobalLinkMainController.getMainController().getHandlerUserListener();

	public void loadFragment(){
		listProjectors = new ListProjectors(equipmentRepository.getListEquipmentByName(language.PROJECTOR(lang), company.getNameCompany()));
		handlerLang.registerObserverLang(listProjectors);
		handlerCompanyListener.registerObserverCompany(listProjectors);
		handlerUserListener.registerObserverUser(listProjectors);

		listProjectors.updateLang(new DataLang(lang));
		listProjectors.updateUser(new DataUser(user));
		listProjectors.updateCompany(new DataCompany(company));

		vBoxTime.getChildren().add(listProjectors.RemTable());
	}

	public void close(MouseEvent mouseEvent) {
		handlerLang.unregisterObserverLang(listProjectors);
		handlerCompanyListener.unregisterObserverCompany(listProjectors);
		handlerUserListener.unregisterObserverUser(listProjectors);
		vBoxTime.getChildren().clear();
	}

	@Override
	public void updateCompany(DataCompany dataCompany) {
		if(dataCompany == null){
			this.company = null;
		} else {
			this.company = dataCompany.getCompany();
			loadFragment();
		}
	}

	@Override
	public void updateLang(DataLang dataLang) {
		this.lang = dataLang.getLanguage();
	}

	@Override
	public void updateUser(DataUser dataUser) {
		if(dataUser == null){
			this.user = null;
		} else this.user = dataUser.getUser();
	}
}
