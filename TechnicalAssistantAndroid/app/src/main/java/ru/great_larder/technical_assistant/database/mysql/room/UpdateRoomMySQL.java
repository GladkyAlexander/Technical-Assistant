package ru.great_larder.technical_assistant.database.mysql.room;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.database.UpdateRoom;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.RoomTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.RoomTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.Room;
import ru.great_larder.technical_assistant.domain.user.User;

public class UpdateRoomMySQL implements UpdateRoom {
    @Override
    public void updateRoom(User user, Room room, Integer id) {
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createRoomTableMySQL();
        RoomTableMySQL roomTableMySQL = new RoomTableMySQLImpl();
        try {
            PreparedStatement cf = connectMySQL.connectionMySQL.prepareStatement(roomTableMySQL.UPDATE(user.getNameDB()));

            cf.setString(1, room.getNameRoom());
            cf.setString(2, room.getNameCompanyForRoom());

            cf.setInt(3, user.getId());

            cf.executeUpdate();
            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }
    }
}
