package ru.greatlarder.technicalassistant.services.database.sqlite.room;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.UpdateRoom;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteRoom;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class UpdateRoomSQLite implements UpdateRoom {
    @Override
    public void updateRoom(User user, Room room, Integer id) {

        connectionDB();

        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteRoom.UPDATE_ROOM);

            cf.setString(1, room.getNameRoom());
            cf.setString(2, room.getNameCompanyForRoom());
            cf.setString(3, room.getUrlLogoRoom());

            cf.setInt(4, id);

            cf.executeUpdate();
           closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }
}
