package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.ScanObj;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.net.ScanNet;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ru.greatlarder.technicalassistant.services.style.StyleSRC.STYLE_DANGER;

public class ItemIpAddress implements ObserverLang, ObserverCompany {
	@FXML public HBox hBox;
	@FXML public TextField net1;
	@FXML public TextField net2;
	@FXML public TextField subNet;
	@FXML public TextField device;
	@FXML public Button btnStart;
	@FXML public BorderPane borderPane;
	@FXML public ImageView imgExit;
	HandlerLang handlerLang = GlobalLinkMainController.mainController.getHandlerLang();
	HandlerCompanyListener handlerCompanyListener = GlobalLinkMainController.mainController.getHandlerCompanyListener();
	private Company company;
	private String lang;
	Language language = new LanguageImpl();
	
	public void start(MouseEvent mouseEvent) {
		if(!net1.getText().isEmpty() && !net2.getText().isEmpty()) {
			Task<Node> task = new Task<Node>() {
				@Override
				protected Node call() throws Exception {
					return getListObj();
				}
			};
			ProgressIndicator progressIndicator = new ProgressIndicator(task.getProgress());
			borderPane.setCenter(progressIndicator);
			task.setOnSucceeded(succeededEvent ->{
				progressIndicator.visibleProperty().bind(task.runningProperty());
				borderPane.setCenter(task.getValue());
			});
			ExecutorService executorService = Executors.newFixedThreadPool(1);
				executorService.execute(task);
				executorService.shutdown();
			
		} else hBox.setStyle(StyleSRC.STYLE_DANGER);
	}
	
	private Node getListObj() {
		ListView<ScanObj> listView = new ListView<>(FXCollections.observableArrayList(
				new ScanNet().start(net1.getText() + "." + net2.getText() + "." + 1 + "." + 0)));
		listView.setCellFactory(param -> new ListCell<>(){
			final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_scan.fxml"));
			Node node;
			ItemScan controller;
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
			protected void updateItem(ScanObj item, boolean empty) {
				super.updateItem(item, empty);
				if(empty){
					setGraphic(null);
				} else {
					handlerLang.registerObserverLang(loader.getController());
					handlerCompanyListener.registerObserverCompany(loader.getController());
					controller.updateLang(new DataLang(lang));
					controller.updateCompany(new DataCompany(company));
					controller.loadFragment(item);
					setGraphic(node);
				}
			}
		});
		
		
		
		return listView;
	}
	
	public void onKeyNet1(KeyEvent keyEvent) {
		hBox.setStyle(new HBox().getStyle());
		if (!net1.getText().isEmpty()) {
			if (checkingForANumber(net1.getText())) {
				if (net1.getText().length() <= 3) {
					if (Integer.parseInt(net1.getText()) <= 256) {
						net1.setStyle(new TextField().getStyle());
					} else net1.setStyle(STYLE_DANGER);
				} else net1.setStyle(STYLE_DANGER);
			} else net1.setStyle(STYLE_DANGER);
		}else net1.setStyle(new TextField().getStyle());
	}
	
	public void onKeyNet2(KeyEvent keyEvent) {
		hBox.setStyle(new HBox().getStyle());
		if (!net2.getText().isEmpty()) {
			if (checkingForANumber(net2.getText())) {
				if (net2.getText().length() <= 3) {
					if (Integer.parseInt(net2.getText()) <= 256) {
						net2.setStyle(new TextField().getStyle());
					} else net2.setStyle(STYLE_DANGER);
				} else net2.setStyle(STYLE_DANGER);
			} else net2.setStyle(STYLE_DANGER);
		}else net2.setStyle(new TextField().getStyle());
	}
	public boolean checkingForANumber(String value) {
		return value.chars().allMatch(Character :: isDigit);
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
		btnStart.setText(language.START(lang));
	}

	public void exit(MouseEvent mouseEvent) {
		borderPane.getChildren().clear();
	}
}
