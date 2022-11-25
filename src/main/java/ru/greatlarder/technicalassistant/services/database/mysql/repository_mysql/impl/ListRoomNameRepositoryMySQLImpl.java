package ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.impl;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.ListRoomNameRepositoryMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.ListRoomNameTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.ListRoomNameTableMySQLImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListRoomNameRepositoryMySQLImpl implements ListRoomNameRepositoryMySQL {
    User user;
    ConnectMySQL connectMySQL;
    @Override
    public void setRoomName(Room room) {

    }

    @Override
    public void setRoomNameList(User user, List<Room> rooms) {
        this.user = user;
        ConnectMySQL connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createListRoomNameTableMySQL();
        ListRoomNameTableMySQL listRoomNameTableMySQL = new ListRoomNameTableMySQLImpl();

        try {
            PreparedStatement ps = connectMySQL.connectionMySQL.prepareStatement(listRoomNameTableMySQL.INSERT(this.user.getNameDB()));

            for (Room room : rooms ){
                ps.setInt(1,room.getId());
                ps.setString(2, room.getNameRoom());
                ps.setString(3, room.getNameCompanyForRoom());
                ps.executeUpdate();
            }
            connectMySQL.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQL_DB();
        }
    }

    @Override
    public List<Room> getRoomsNameList(User user, String companyName) {
        this.user = user;
        List<Room> result = new ArrayList<Room>();
        connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createListRoomNameTableMySQL();
        ListRoomNameTableMySQL listRoomNameTableMySQL = new ListRoomNameTableMySQLImpl();

        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(listRoomNameTableMySQL.SELECT(this.user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()){
                if (companyName.equals(connectMySQL.resultSetMySQL.getString("nameCompany"))){
                    result.add(getRoomN(connectMySQL.resultSetMySQL));
                }
            }
            connectMySQL.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQL_DB();
        }

        return result;
    }

    private Room getRoomN(ResultSet resultSetMySQL) throws SQLException {

        Room room = new Room();
        room.setNameRoom(resultSetMySQL.getString("nameRoom"));
        room.setNameCompanyForRoom(resultSetMySQL.getString("nameCompany"));
        return room;

    }

    @Override
    public Room getRoomsName(String companyName, String roomName) {
        return null;
    }

    @Override
    public void updateRoomName(Room room) {

    }

    @Override
    public void updateRoomNameList(List<Room> rooms) {

    }
}
