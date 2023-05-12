package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.Names;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetNamesServiceImpl implements GetNamesService{
    @Override
    public Names getNames(ResultSet resultSet) throws SQLException {
        Names names = new Names();

        names.setId(resultSet.getInt("id"));
        names.setNames(resultSet.getString("names"));
        names.setNameCompany(resultSet.getString("nameCompany"));
        
        names.setDomain(resultSet.getString("domain"));
        
        byte b[];
        Blob blob;
        FileManager fileManager = new FileManagerImpl();
        File f=new File(fileManager.folderImage() + "\\" + names.getNames() + ".png");
        try {
            FileOutputStream fs=new FileOutputStream(f);
            blob=resultSet.getBlob("url");
            b=blob.getBytes(1, (int)blob.length());
            fs.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        names.setUrl(f);
        
        return names;
    }
}
