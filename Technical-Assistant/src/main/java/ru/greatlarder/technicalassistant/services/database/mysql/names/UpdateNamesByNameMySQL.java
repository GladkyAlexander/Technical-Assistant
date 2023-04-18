package ru.greatlarder.technicalassistant.services.database.mysql.names;

import ru.greatlarder.technicalassistant.domain.Names;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.NamesTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.NamesTableMySQLImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateNamesByNameMySQL implements ru.greatlarder.technicalassistant.services.database.UpdateNames {
    @Override
    public void updateNames(User user, Names updates) {
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createNamesTableMySQL();
        NamesTableMySQL namesTableMySQL = new NamesTableMySQLImpl();

        try {
            PreparedStatement ps = connectMySQL.connectionMySQL.prepareStatement(namesTableMySQL.UPDATE(user.getNameDB()));

            ps.setString(1, updates.getNames());
            ps.setString(2, updates.getNameCompany());
            ps.setString(3, updates.getUrl());
            ps.setString(4, updates.getDomain());

            ps.setString(5, updates.getNames());

            ps.executeUpdate();
            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }
    }
}
