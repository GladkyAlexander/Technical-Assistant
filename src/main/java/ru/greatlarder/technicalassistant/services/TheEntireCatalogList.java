package ru.greatlarder.technicalassistant.services;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import ru.greatlarder.technicalassistant.controller.fragment.FragmentFolderItemController;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;
import ru.greatlarder.technicalassistant.services.work_doc.ReadExel;

import java.io.IOException;

public class TheEntireCatalogList {
    FileManager fileManager = new FileManagerImpl();
    String company;

    public ListView upVbox(String nameCompany) {
        this.company = nameCompany;

        ObservableList<String> list = FXCollections.observableArrayList(fileManager.getListOfFileNamesInTheDirectory(nameCompany, "Documentations"));

        ListView<String> listView = new ListView<>(list);
        listView.setCellFactory(param -> new ListCell<>() {
            private Node grafic;
            private FragmentFolderItemController fragmentFolderItem;

            {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentFolderItem.fxml"));
                    grafic = loader.load();
                    fragmentFolderItem = loader.getController();
                    setPrefWidth(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    fragmentFolderItem.setLabelNameFolder(item);
                    if (item.substring(item.lastIndexOf('.') + 1).equals("pdf")) {
                        fragmentFolderItem.setImgFolder("/ru/greatlarder/technicalassistant/images/doc.png");
                    }
                    if (item.substring(item.lastIndexOf('.') + 1).equals("xls") || item.substring(item.lastIndexOf('.') + 1).equals("xlsx")) {
                        fragmentFolderItem.setImgFolder("/ru/greatlarder/technicalassistant/images/exel.png");
                    }
                    if (item.substring(item.lastIndexOf('.') + 1).equals("docx")) {
                        fragmentFolderItem.setImgFolder("/ru/greatlarder/technicalassistant/images/word.png");
                    }
                    setGraphic(grafic);
                }
            }
        });

        listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //ManualFragmentController manualFragmentController;

                String s = fileManager.getUrlFileDocumentations(company,
                        listView.getSelectionModel().getSelectedItem());

                FXMLLoader loader;

                if (s.substring(s.lastIndexOf('.') + 1).equals("xls") || s.substring(s.lastIndexOf('.') + 1).equals("xlsx")) {
                    ReadExel f = new ReadExel();
                    System.out.println(f.parse(s) + "============================zdes 79 tec");
                }
                if (s.substring(s.lastIndexOf('.') + 1).equals("pdf")) {
                    loader = new FXMLLoader(getClass().getResource("/layout/manualFragment.fxml"));
                    try {

                        Scene scene = new Scene(loader.load());
                        Stage stage = new Stage();

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        return listView;
    }
}
