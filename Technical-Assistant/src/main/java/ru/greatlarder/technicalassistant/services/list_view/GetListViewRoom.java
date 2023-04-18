package ru.greatlarder.technicalassistant.services.list_view;

import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface GetListViewRoom {
    ListView<Room> getListView(User user, String nameCompany, String value);

}
