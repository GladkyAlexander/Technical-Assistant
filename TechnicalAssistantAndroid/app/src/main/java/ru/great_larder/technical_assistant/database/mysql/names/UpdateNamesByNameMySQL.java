package ru.great_larder.technical_assistant.database.mysql.names;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.database.UpdateNames;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.NamesTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.NamesTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.Names;
import ru.great_larder.technical_assistant.domain.user.User;

public class UpdateNamesByNameMySQL implements UpdateNames {
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
