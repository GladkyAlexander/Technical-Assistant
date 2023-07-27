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
    FileManager fileManager = new FileManagerImpl();
    @Override
    public Room getRoom(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        
        room.setId(resultSet.getInt("id"));
        room.setNameRoom(resultSet.getString("nameRoom"));
        room.setNameCompanyForRoom(resultSet.getString("nameCompany"));
        
        byte b[];
        Blob blob;
        
        File f=new File(fileManager.folderImage() + "\\" + room.getNameRoom() + ".png");
        try {
            FileOutputStream fs = new FileOutputStream(f);
            blob = resultSet.getBlob("image");
            b = blob.getBytes(1, (int)blob.length());
            fs.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        room.setUrlLogoRoom(fileManager.folderImage() + "\\" + room.getNameRoom() + ".png");
        
        byte b_pdf[];
        Blob blob_pdf;
        File f_pdf = new File(fileManager.folderCompanyInstruction(room.getNameCompanyForRoom()) + "\\" + room.getNameRoom() + ".pdf");
        try {
            FileOutputStream fs_pdf = new FileOutputStream(f_pdf);
            blob_pdf = resultSet.getBlob("instruction");
            b_pdf = blob_pdf.getBytes(1, (int)blob_pdf.length());
            fs_pdf.write(b_pdf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        room.setInstruction(fileManager.folderCompanyInstruction(room.getNameCompanyForRoom()) + "\\" + room.getNameRoom() + ".pdf");
        
        return room;
    }
}
