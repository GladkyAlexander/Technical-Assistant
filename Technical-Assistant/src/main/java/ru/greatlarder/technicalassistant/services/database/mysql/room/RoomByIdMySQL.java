package ru.greatlarder.technicalassistant.services.database.mysql.room;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.general.GetRoomServiceImplMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.RoomTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.RoomTableMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.general.GetRoomService;
import ru.greatlarder.technicalassistant.services.database.general.GetRoomServiceImplSQLite;
import ru.greatlarder.technicalassistant.services.database.GetRoom;

import java.sql.SQLException;
import java.util.Objects;

public class RoomByIdMySQL implements GetRoom {
    @Override
    public Room getRoom(User user, String nameCompany, String idRoom) {
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createRoomTableMySQL();
        RoomTableMySQL roomTableMySQL = new RoomTableMySQLImpl();

        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(roomTableMySQL.SELECT(user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()) {
                if(Objects.equals(connectMySQL.resultSetMySQL.getInt("id"), Integer.valueOf(idRoom))){
                    GetRoomService getRoomService = new GetRoomServiceImplMySQL();
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
