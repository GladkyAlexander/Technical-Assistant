package ru.great_larder.technical_assistant.database.mysql.room;

import java.sql.SQLException;
import java.util.Objects;

import ru.great_larder.technical_assistant.database.GetRoom;
import ru.great_larder.technical_assistant.database.general.GetRoomService;
import ru.great_larder.technical_assistant.database.general.GetRoomServiceImpl;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.RoomTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.RoomTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.Room;
import ru.great_larder.technical_assistant.domain.user.User;

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
                    GetRoomService getRoomService = new GetRoomServiceImpl();
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
