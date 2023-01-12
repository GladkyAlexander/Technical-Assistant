package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.controller.fragment.FragmentEquipmentOneController;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.ScanObj;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.EquipmentRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.EquipmentRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.net.GetMacAddress;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ItemScan implements ObserverLang, ObserverCompany {
	@FXML public Label ip;
	@FXML public Label mac;
	@FXML public Label manufacturer;
	@FXML public GridPane gridPane;
	@FXML public ImageView imgLogo;
	EquipmentRepository equipmentRepository = new EquipmentRepositoryImpl();
	private Company company;
	private String lang;
	private Equipment equipment;
	HandlerLang handlerLang = GlobalLinkMainController.mainController.getHandlerLang();
	
	public void loadFragment(ScanObj scanObj){
		ip.setText(scanObj.getIp());
		mac.setText(scanObj.getMac());
		loadUi(false);
		if(scanObj.getMac() != null){
			if (equipmentRepository.getEquipmentByMacAddress(company.getNameCompany(), scanObj.getMac()) != null){
				this.equipment = equipmentRepository.getEquipmentByMacAddress(company.getNameCompany(), scanObj.getMac());
				loadEquipment(this.equipment);
			} else getManyfacturer(scanObj.getMac());
		}
	}
	
	private void loadEquipment(Equipment equip) {
		loadUi(true);
		if(!equip.getImage().isEmpty()){
			imgLogo.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/equipment_img/" + equip.getImage()))));
		}
		ip.setText(equip.getIpAddress());
		mac.setText(equip.getMacAddress());
		manufacturer.setText(equip.getManufacturer());
	}
	
	private void getManyfacturer(String mac) {
		Task<String> task = new Task<String>() {
			
			@Override
			protected String call() throws Exception {
				return GetMacAddress.getDataByMac(mac);
			}
		};
		ProgressBar progressIndicator = new ProgressBar(task.getProgress());
		gridPane.add(progressIndicator, 0, 2);
		task.setOnSucceeded(succeededEvent ->{
			progressIndicator.visibleProperty().bind(task.runningProperty());
			manufacturer.setText(task.getValue());
		});
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
		executorService.shutdown();
	}
	private void loadUi(boolean a){
		imgLogo.setVisible(a);
		imgLogo.setManaged(a);
	}
	@Override
	public void updateCompany(DataCompany dataCompany) {
		if(dataCompany == null){
			this.company = null;
		} else this.company = dataCompany.getCompany();
	}
	@Override
	public void updateLang(DataLang dataLang) {
		this.lang = dataLang.getLanguage();
	}
	
	public void openEquipment(MouseEvent mouseEvent) {
		if(equipment != null){
			FXMLLoader loader = new FXMLLoader(getClass()
					.getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEquipmentOne.fxml"));
			try {
				GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
				handlerLang.registerObserverLang(loader.getController());
				handlerLang.onNewDataLang(new DataLang(lang));
				FragmentEquipmentOneController fragmentEquipmentOneItemController = loader.getController();
				fragmentEquipmentOneItemController.setEquip(equipment);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
