package ru.greatlarder.technicalassistant.services.tasks;

import javafx.concurrent.Task;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListRoom;
import ru.greatlarder.technicalassistant.services.database.mysql.room.ListRoomByCompanyMySQL;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;

import java.util.List;

public class RoomsTask extends Task<List<Room>> {
    User user = GlobalLinkMainController.getMainController().getUser();
    Company company = GlobalLinkMainController.getMainController().getCompany();
    @Override
    protected List<Room> call() throws Exception {

        GetListRoom getListRoom = new ListRoomByCompanyMySQL();

        return getListRoom.getListRoom(user, company.getNameCompany(), null);
    }
}
