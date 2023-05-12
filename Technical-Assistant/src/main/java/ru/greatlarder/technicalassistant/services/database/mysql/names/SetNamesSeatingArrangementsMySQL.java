package ru.greatlarder.technicalassistant.services.database.mysql.names;

import org.w3c.dom.Document;
import ru.greatlarder.technicalassistant.domain.Names;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.SetNames;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.NamesTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.NamesTableMySQLImpl;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SetNamesSeatingArrangementsMySQL implements SetNames {
    @Override
    public Integer setNames(User user, String nameCompany, Names names) {
        ConnectMySQL connection = new ConnectMySQL(user);
        connection.createNamesTableMySQL();
        NamesTableMySQL namesTableMySQL = new NamesTableMySQLImpl();
        try {
            PreparedStatement ps = connection.connectionMySQL.prepareStatement(namesTableMySQL.INSERT(user.getNameDB()),  Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, names.getNames());
            ps.setString(2, names.getNameCompany());
            /*ps.setString(3, names.getUrl());*/
            ByteArrayInputStream bais = null;
            try {
                bais = new ByteArrayInputStream(getByteArrayFromFile(names.getUrl()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            ps.setBlob(3, bais);
            ps.setString(4, "seating_arrangements");

            if(ps.executeUpdate() > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
            connection.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeMySQLDatabase();
        }
        return null;
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
