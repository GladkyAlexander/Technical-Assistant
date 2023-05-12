package ru.greatlarder.technicalassistant.services.database.mysql.names;

import ru.greatlarder.technicalassistant.domain.Names;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.UpdateNames;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.NamesTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.NamesTableMySQLImpl;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateNamesByIdMySQL implements UpdateNames {
    @Override
    public void updateNames(User user, Names updates, String value) {
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createNamesTableMySQL();
        NamesTableMySQL namesTableMySQL = new NamesTableMySQLImpl();
        
        try {
            PreparedStatement ps = connectMySQL.connectionMySQL.prepareStatement(namesTableMySQL.UPDATE_BY_ID(user.getNameDB()));
            
            ps.setString(1, updates.getNames());
            ps.setString(2, updates.getNameCompany());
            ByteArrayInputStream bais = null;
            try {
                bais = new ByteArrayInputStream(getByteArrayFromFile(updates.getUrl()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ps.setBlob(3, bais);
            ps.setString(4, updates.getDomain());
            
            ps.setInt(5, Integer.parseInt(value));
            
            ps.executeUpdate();
            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }
    }
    private byte[] getByteArrayFromFile(final File handledDocument) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final InputStream in = new FileInputStream((File) handledDocument);
        final byte[] buffer = new byte[500];
        
        int read = -1;
        while ((read = in.read(buffer)) > 0) {
            baos.write(buffer, 0, read);
        }
        in.close();
        
        return baos.toByteArray();
    }
}
