package ru.greatlarder.technicalassistant.services.database.mysql.room;

import org.apache.commons.io.FileUtils;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.RoomTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.RoomTableMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.SetRoom;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
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
