package ru.greatlarder.technicalassistant.services.database.mysql.room;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.general.GetRoomServiceImplMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.RoomTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.RoomTableMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.general.GetRoomService;
import ru.greatlarder.technicalassistant.services.database.general.GetRoomServiceImplSQLite;
import ru.greatlarder.technicalassistant.services.database.GetListRoom;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListRoomByCompanyMySQL implements GetListRoom {
    @Override
    public List<Room> getListRoom(User user, String nameCompany, String value) {
        List<Room> rooms = new ArrayList<>();
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createRoomTableMySQL();
        RoomTableMySQL roomTableMySQL = new RoomTableMySQLImpl();

        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(roomTableMySQL.SELECT(user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()) {
                if(Objects.equals(connectMySQL.resultSetMySQL.getString("nameCompany"), nameCompany)){
                    GetRoomService getRoomService = new GetRoomServiceImplMySQL();
                    rooms.add(getRoomService.getRoom(connectMySQL.resultSetMySQL));
                }
            }
            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }
        return rooms;
    }
}
