package ru.greatlarder.technicalassistant.services.database.mysql.room;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.UpdateRoom;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.RoomTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.RoomTableMySQLImpl;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateRoomMySQL implements UpdateRoom {
    @Override
    public void updateRoom(User user, Room room, Integer id) {
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createRoomTableMySQL();
        RoomTableMySQL roomTableMySQL = new RoomTableMySQLImpl();
        try {
            PreparedStatement cf = connectMySQL.connectionMySQL.prepareStatement(roomTableMySQL.UPDATE(user.getNameDB()));

            cf.setString(1, room.getNameRoom());
            
            ByteArrayInputStream bais = null;
            try {
                Path path = Paths.get(room.getUrlLogoRoom());
                File file = path.toFile();
                bais = new ByteArrayInputStream(getByteArrayFromFile(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
            cf.setBlob(2, bais);
            cf.setString(3, room.getNameCompanyForRoom());
            
            try {
                Path path = Paths.get(room.getInstruction());
                File file1 = path.toFile();
                bais = new ByteArrayInputStream(getByteArrayFromFile(file1));
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            cf.setBlob(4, bais);

            cf.setInt(5, id);

            cf.executeUpdate();
            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }
    }
    private byte[] getByteArrayFromFile(final File handledDocument) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final InputStream in = new FileInputStream(handledDocument);
        final byte[] buffer = new byte[500];
        
        int read = -1;
        while ((read = in.read(buffer)) > 0) {
            baos.write(buffer, 0, read);
        }
        in.close();
        
        return baos.toByteArray();
    }
}
