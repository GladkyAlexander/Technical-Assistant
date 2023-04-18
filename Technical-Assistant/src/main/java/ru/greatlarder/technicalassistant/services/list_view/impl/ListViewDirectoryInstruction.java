package ru.greatlarder.technicalassistant.services.list_view.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemFolderController;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.list_view.GetAListOfFilesFromTheDirectory;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.io.File;
import java.io.IOException;

public class ListViewDirectoryInstruction implements GetAListOfFilesFromTheDirectory {
    FileManager fileManager = new FileManagerImpl();
    String company;
    @Override
    public ListView<String> getListViewFile(User user, String nameCompany) {
        this.company = nameCompany;

        ObservableList<String> list = FXCollections.observableArrayList(fileManager.getListOfFileNamesInTheDirectory(nameCompany, "Instructions"));

        ListView<String> listView = new ListView<>(list);
        listView.setCellFactory(param -> new ListCell<>() {

            private Node grafic;
            private ItemFolderController fragmentFolderItem;
            {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_folder.fxml"));
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
                    File file = new File(fileManager.getUrlFileInstructions(company, item));
                    fragmentFolderItem.setFile(file);
                    setGraphic(grafic);
                }
            }
        });

        return listView;
    }
}
