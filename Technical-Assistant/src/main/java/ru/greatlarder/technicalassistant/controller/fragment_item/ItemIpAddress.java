package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.ScanObj;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.net.ScanNet;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ru.greatlarder.technicalassistant.services.style.StyleSRC.STYLE_DANGER;

public class ItemIpAddress implements Initializable {
	@FXML public HBox hBox;
	@FXML public TextField net1;
	@FXML public TextField net2;
	@FXML public TextField subNet;
	@FXML public TextField device;
	@FXML public Button btnStart;
	@FXML public BorderPane borderPane;
	@FXML public ImageView imgExit;
	Company company;
	String lang;
	Language language = new LanguageImpl();
	User user;

	public void start(MouseEvent mouseEvent) {

		if(!net1.getText().isEmpty() && !net2.getText().isEmpty()) {
			Task<Node> task = new Task<Node>() {
				@Override
				protected Node call() throws Exception {
					return getListObj();
				}
			};
			ProgressIndicator progressIndicator = new ProgressIndicator();
			borderPane.setCenter(progressIndicator);
			task.setOnSucceeded(succeededEvent ->{
				borderPane.setCenter(task.getValue());
			});
			Platform.runLater(()->{
				progressIndicator.setProgress(task.getProgress());
				progressIndicator.visibleProperty().bind(task.runningProperty());
				ExecutorService executorService = Executors.newFixedThreadPool(1);
				executorService.execute(task);
				executorService.shutdown();
			});

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

	public void exit(MouseEvent mouseEvent) {
		borderPane.getChildren().clear();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.company = GlobalLinkMainController.getMainController().getCompany();
		this.user = GlobalLinkMainController.getMainController().getUser();
		this.lang = GlobalLinkMainController.getMainController().getLang();
		btnStart.setText(language.START(lang));
		String[] words = new String[0];
		try {
			words = Inet4Address.getLocalHost().getHostAddress().split("\\.");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		List<String> s = new ArrayList<String>(Arrays.asList(words));
		net1.setText(s.get(0));
		net2.setText(s.get(1));
	}
}
