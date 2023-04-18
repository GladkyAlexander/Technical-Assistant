package ru.greatlarder.technicalassistant.services.database.sqlite.room;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListRoom;
import ru.greatlarder.technicalassistant.services.database.general.GetRoomService;
import ru.greatlarder.technicalassistant.services.database.general.GetRoomServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteRoom;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class ListRoomByCompanySQLite implements GetListRoom {
    @Override
    public List<Room> getListRoom(User user, String nameCompany, String value) {
        List<Room> rooms = new ArrayList<Room>();
        createRoomTable();

        GetRoomService getRoomService = new GetRoomServiceImpl();
        try {
            resultSet = statement.executeQuery(SQLiteRoom.READ_ROOM);
            while (resultSet.next()){
                if(resultSet.getString("nameCompany").equals(nameCompany)){
                    rooms.add(getRoomService.getRoom(resultSet));
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return rooms;
    }
}
