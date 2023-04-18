package ru.greatlarder.technicalassistant.services.database.sqlite.room;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.SetRoom;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteRoom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class SetRoomSQLite implements SetRoom {
    @Override
    public Integer setRoom(User user, String nameCompany, Room room) {
        Integer idRoom = null;

        createRoomTable();
        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteRoom.INSERT_ROOM, Statement.RETURN_GENERATED_KEYS);

            cf.setString(1, room.getNameRoom());
            cf.setString(2, room.getNameCompanyForRoom());
            if(cf.executeUpdate() > 0){
                ResultSet rs = cf.getGeneratedKeys();
                if(rs.next()){
                    idRoom = rs.getInt(1);
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return idRoom;
    }
}
