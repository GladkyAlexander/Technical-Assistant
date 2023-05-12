package ru.greatlarder.technicalassistant.services.database.sqlite.room;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetRoom;
import ru.greatlarder.technicalassistant.services.database.general.GetRoomService;
import ru.greatlarder.technicalassistant.services.database.general.GetRoomServiceImplSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteRoom;

import java.sql.SQLException;
import java.util.Objects;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class RoomByIdSQLite implements GetRoom {
    @Override
    public Room getRoom(User user, String nameCompany, String idRoom) {
        createRoomTable();
        GetRoomService getRoomService = new GetRoomServiceImplSQLite();
        try {
            resultSet = statement.executeQuery(SQLiteRoom.READ_ROOM);
            while (resultSet.next()){
                if(Objects.equals(resultSet.getInt("id"), Integer.valueOf(idRoom))){
                    return getRoomService.getRoom(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null;
    }
}
