package ru.great_larder.technical_assistant.database.mysql.room;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.great_larder.technical_assistant.database.GetListRoom;
import ru.great_larder.technical_assistant.database.general.GetRoomService;
import ru.great_larder.technical_assistant.database.general.GetRoomServiceImpl;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.RoomTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.RoomTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.Room;
import ru.great_larder.technical_assistant.domain.user.User;

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
                    GetRoomService getRoomService = new GetRoomServiceImpl();
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
