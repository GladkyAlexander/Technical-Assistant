package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.repository.EquipmentRepository;
import ru.greatlarder.technicalassistant.repository.impl.EquipmentRepositoryImpl;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.tables.ListProjectors;

public class FragmentAddWatchWorkProjectorsController implements ObserverLang, ObserverCompany {

	@FXML public VBox vBoxTime;
	@FXML public ImageView close;
	EquipmentRepository equipmentRepository = new EquipmentRepositoryImpl();
	ListProjectors listProjectors;
	private Company company;
	private String lang;
	Language language = new LanguageImpl();
	HandlerLang handlerLang = new HandlerLang();
	HandlerCompanyListener handlerCompanyListener = new HandlerCompanyListener();

	public void loadFragment(){
		listProjectors = new ListProjectors(equipmentRepository.getListEquipmentByName(language.PROJECTOR(lang), company.getNameCompany()));
		handlerLang.registerObserverLang(listProjectors);
		handlerCompanyListener.registerObserverCompany(listProjectors);
		handlerLang.onNewDataLang(new DataLang(lang));
		handlerCompanyListener.onNewDataCompany(new DataCompany(company));

		vBoxTime.getChildren().add(listProjectors.RemTable());
	}

	public void close(MouseEvent mouseEvent) {
		vBoxTime.getChildren().clear();
	}

	@Override
	public void updateCompany(DataCompany dataCompany) {
		this.company = dataCompany.getCompany();
		handlerCompanyListener.onNewDataCompany(new DataCompany(company));
	}

	@Override
	public void updateLang(DataLang dataLang) {
		this.lang = dataLang.getLanguage();
		handlerLang.onNewDataLang(new DataLang(lang));
	}
}
