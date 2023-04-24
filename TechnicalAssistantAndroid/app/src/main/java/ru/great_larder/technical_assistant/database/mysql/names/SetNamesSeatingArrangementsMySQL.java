package ru.great_larder.technical_assistant.database.mysql.names;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ru.great_larder.technical_assistant.database.SetNames;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.NamesTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.NamesTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.Names;
import ru.great_larder.technical_assistant.domain.user.User;

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
            ps.setString(3, names.getUrl());
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
}
