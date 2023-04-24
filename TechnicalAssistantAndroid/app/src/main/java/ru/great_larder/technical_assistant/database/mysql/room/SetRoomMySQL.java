package ru.great_larder.technical_assistant.database.mysql.room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ru.great_larder.technical_assistant.database.SetRoom;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.RoomTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.RoomTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.Room;
import ru.great_larder.technical_assistant.domain.user.User;

public class SetRoomMySQL implements SetRoom {
    @Override
    public Integer setRoom(User user, String nameCompany, Room room) {
        Integer idRoom = null;
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createRoomTableMySQL();

        RoomTableMySQL roomTableMySQL = new RoomTableMySQLImpl();
        try {
            PreparedStatement cf = connectMySQL.connectionMySQL.prepareStatement(roomTableMySQL.INSERT(user.getNameDB()), Statement.RETURN_GENERATED_KEYS);
            cf.setInt(1, room.getId());
            cf.setString(2, room.getNameRoom());
            cf.setString(3, room.getNameCompanyForRoom());

            if(cf.executeUpdate() > 0){
                ResultSet rs = cf.getGeneratedKeys();
                if(rs.next()){
                    idRoom = rs.getInt(1);
                }
            }
            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }

        return idRoom;
    }
}
