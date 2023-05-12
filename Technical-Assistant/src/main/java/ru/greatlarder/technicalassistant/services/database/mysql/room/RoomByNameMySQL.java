package ru.greatlarder.technicalassistant.services.database.mysql.room;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetRoom;
import ru.greatlarder.technicalassistant.services.database.general.GetRoomService;
import ru.greatlarder.technicalassistant.services.database.general.GetRoomServiceImplSQLite;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.RoomTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.RoomTableMySQLImpl;

import java.sql.SQLException;

public class RoomByNameMySQL implements GetRoom {
    @Override
    public Room getRoom(User user, String nameCompany, String nameRoom) {
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createRoomTableMySQL();
        RoomTableMySQL roomTableMySQL = new RoomTableMySQLImpl();
        
        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(roomTableMySQL.SELECT(user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()) {
                if(connectMySQL.resultSetMySQL.getString("nameRoom").equals(nameRoom)
                && connectMySQL.resultSetMySQL.getString("nameCompany").equals(nameCompany)){
                    GetRoomService getRoomService = new GetRoomServiceImplSQLite();
                    return getRoomService.getRoom(connectMySQL.resultSetMySQL);
                }
            }
            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }
        return null;
    }
}
