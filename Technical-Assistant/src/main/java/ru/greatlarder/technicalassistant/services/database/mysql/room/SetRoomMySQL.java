package ru.greatlarder.technicalassistant.services.database.mysql.room;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.RoomTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.RoomTableMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.SetRoom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
