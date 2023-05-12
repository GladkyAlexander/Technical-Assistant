package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetRoomServiceImplMySQL implements GetRoomService{
    @Override
    public Room getRoom(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        
        room.setId(resultSet.getInt("id"));
        room.setNameRoom(resultSet.getString("nameRoom"));
        room.setNameCompanyForRoom(resultSet.getString("nameCompany"));
        
        
        
//        room.setUrlLogoRoom(resultSet.getString("image"));
        
        byte b[];
        Blob blob;
        FileManager fileManager = new FileManagerImpl();
        File f=new File(fileManager.folderImage() + "\\" + room.getNameRoom() + ".png");
        try {
            FileOutputStream fs=new FileOutputStream(f);
            blob=resultSet.getBlob("image");
            b=blob.getBytes(1, (int)blob.length());
            fs.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        room.setUrlLogoRoom(fileManager.folderImage() + "\\" + room.getNameRoom() + ".png");
        
        return room;
    }
}
