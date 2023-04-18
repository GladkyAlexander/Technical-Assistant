package ru.greatlarder.technicalassistant.services.list_view;

import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface GetAListOfFilesFromTheDirectory {
    ListView<String> getListViewFile(User user, String nameCompany);
}
