package ru.greatlarder.technicalassistant.controller.engineer;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.*;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.MailSettingsRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.MailSettingsRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.mail.Mail;
import ru.greatlarder.technicalassistant.services.tables.ListMail;
import ru.greatlarder.technicalassistant.services.tables.ListTask;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Long.MAX_VALUE;
import static java.time.temporal.ChronoUnit.DAYS;

public class HomeEngineerController implements ObserverLang, ObserverUser, ObserverCompany {
	@FXML
	public Label labelQuantityEquipment;
	@FXML
	public Label labelQuantityDefectEquipment;
	@FXML
	public Label labelQuantityEquipmentWorkFive;
	@FXML
	public Label labelQuantityTheTool;
	@FXML
	public BorderPane borderPaneHomePage;
	@FXML
	public GridPane gridPaneHomeFragment;
	@FXML
	public SplitPane splitPaneHF;
	@FXML
	public Label labelNumberOfDevices;
	@FXML
	public Label labelNumberOfFaultyDevices;
	@FXML
	public Label labelNumberOfDevicesOperatingForMoreThanFiveYears;
	@FXML
	public Label labelNumberOfTools;
	@FXML
	public TabPane tabPaneEngineerHome;
	Language language = new LanguageImpl();
	String lang;
	User user;
	private Company company;
	HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
	HandlerCompanyListener handlerCompanyListener = GlobalLinkStartEngineerController.getStartEngineerController().handlerCompanyListener;
	
	@Override
	public void updateCompany(DataCompany dataCompany) {
		if (dataCompany == null) {
			this.company = null;
		} else {
			this.company = dataCompany.getCompany();
			if (this.company != null) {
				loadFragment();
			}
		}
		
	}
	
	@Override
	public void updateLang(DataLang dataLang) {
		this.lang = dataLang.getLanguage();
		setLanguage(lang);
	}
	
	@Override
	public void updateUser(DataUser dataUser) {
		if (dataUser == null) {
			this.user = null;
		} else this.user = dataUser.getUser();
	}
	
	private void setLanguage(String lang) {
		labelNumberOfDevices.setText(language.NUMBER_OF_DEVICES(lang));
		labelNumberOfFaultyDevices.setText(language.NUMBER_OF_FAULTY_DEVICES(lang));
		labelNumberOfDevicesOperatingForMoreThanFiveYears.setText(language.THE_NUMBER_OF_DEVICES_OPERATING_FOR_MORE_THAN_5_YEARS(lang));
		labelNumberOfTools.setText(language.NUMBER_OF_TOOLS(lang));
	}
	
	private void setNumberOfDevice(List<Equipment> equipmentList) {
		labelQuantityEquipment.setText(String.valueOf(equipmentList.size()));
	}
	
	private void setNumberOfFaultyDevices(List<Equipment> equipmentList) {
		List<Equipment> faultyDevicesList = new ArrayList<>();
		for (Equipment equipment : equipmentList) {
			if (equipment.getCondition().equals("Faulty") || equipment.getCondition().equals("Неисправно")) {
				faultyDevicesList.add(equipment);
			}
		}
		labelQuantityDefectEquipment.setText(String.valueOf(faultyDevicesList.size()));
	}
	
	private void setNumberOfDevicesOperatingForMoreThanFiveYears(List<Equipment> equipmentList) {
		List<Equipment> devicesOperatingForMoreThanFiveYears = new ArrayList<>();
		for (Equipment equipment : equipmentList) {
			LocalDate from = LocalDate.now();
			LocalDate to = LocalDate.parse(String.valueOf(equipment.getDateWork()));
			long day = DAYS.between(to, from);
			if (day > 1825) {
				devicesOperatingForMoreThanFiveYears.add(equipment);
			}
		}
		labelQuantityEquipmentWorkFive.setText(String.valueOf(devicesOperatingForMoreThanFiveYears.size()));
	}
	
	private void setNumberOfTools(List<Tool> toolList) {
		labelQuantityTheTool.setText(String.valueOf(toolList.size()));
	}
	
	public void loadFragment() {
			setNumberOfDevice(company.getEquipmentList());
			setNumberOfFaultyDevices(company.getEquipmentList());
			setNumberOfDevicesOperatingForMoreThanFiveYears(company.getEquipmentList());
			setNumberOfTools(company.getToolList());

			if (user != null) {
				if(company.getTaskList() != null) {
					tabPaneEngineerHome.getTabs().add(loadTasksActive(company.getTaskList()));
					tabPaneEngineerHome.getTabs().add(loadTasksAll(company.getTaskList()));
				}
				MailSettingsRepository mailSettingsRepository = new MailSettingsRepositoryImpl();
				MailSettings ms = mailSettingsRepository.getMailSettingsByIdUser(user.getId());
				if (ms != null) {
					loadMail(ms);
				}
			}
	}
	private Tab loadTasksActive(List<Affairs> affairsList) {
		Tab t = new Tab(language.ALL_ACTIVE_APPLICATIONS(lang));
		javafx.concurrent.Task<ListView<Affairs>> ts = new javafx.concurrent.Task<ListView<Affairs>>() {
			@Override
			protected ListView<Affairs> call() throws Exception {
				List<Affairs> taskActiveList = new ArrayList<>();
				for (Affairs task : affairsList) {
					if (task.getStatus() == 1) {
						taskActiveList.add(task);
					}
				}
				return new ListTask().upBox(taskActiveList);
			}
		};
		ProgressBar progressBar = new ProgressBar(ts.getProgress());
		progressBar.setMaxWidth(MAX_VALUE);
		t.setContent(progressBar);
		ts.setOnSucceeded((succeededEvent) -> {
			progressBar.visibleProperty().bind(ts.runningProperty());
			t.setContent(ts.getValue());
		});
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(ts);
		executorService.shutdown();
		return t;
	}
	private Tab loadTasksAll(List<Affairs> affairsList) {
		Tab tA = new Tab(language.ALL_ACTIVE_APPLICATIONS(lang));
		javafx.concurrent.Task<ListView<Affairs>> ts = new javafx.concurrent.Task<ListView<Affairs>>() {
			@Override
			protected ListView<Affairs> call() throws Exception {
				return new ListTask().upBox(affairsList);
			}
		};
		ProgressBar progressBar = new ProgressBar(ts.getProgress());
		progressBar.setMaxWidth(MAX_VALUE);
		tA.setContent(progressBar);
		ts.setOnSucceeded((succeededEvent) -> {
			progressBar.visibleProperty().bind(ts.runningProperty());
			tA.setContent(ts.getValue());
		});
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(ts);
		executorService.shutdown();
		return tA;
	}
	
	
	
	private void loadMail(MailSettings mailSettings) {
		synchronized (this) {
			javafx.concurrent.Task<ListView<Affairs>> task = new javafx.concurrent.Task<ListView<Affairs>>() {
				@Override
				protected ListView<Affairs> call() throws Exception {
					ListMail listMail = new ListMail();
					handlerLang.registerObserverLang(listMail);
					handlerCompanyListener.registerObserverCompany(listMail);
					listMail.updateLang(new DataLang(lang));
					listMail.updateCompany(new DataCompany(company));
					Mail mail = new Mail(mailSettings);
					return listMail.upBox(mail.getListOfTasks());
				}
			};
			ProgressBar progressBar = new ProgressBar(task.getProgress());
			progressBar.setMaxWidth(MAX_VALUE);
			progressBar.setMaxHeight(MAX_VALUE);
			GlobalLinkMainController.getMainController().hBoxTopToolbar.getChildren().add(progressBar);
			task.setOnSucceeded((succeededEvent) -> {
				progressBar.visibleProperty().bind(task.runningProperty());
				splitPaneHF.setDividerPositions(0.15f, 0, 85f);
				splitPaneHF.getItems().add(task.getValue());
			});
			ExecutorService executorService = Executors.newFixedThreadPool(1);
			executorService.execute(task);
			executorService.shutdown();
		}
	}

}
