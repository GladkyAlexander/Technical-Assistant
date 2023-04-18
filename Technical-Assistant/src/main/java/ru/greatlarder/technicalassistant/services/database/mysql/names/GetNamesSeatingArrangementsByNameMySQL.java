package ru.greatlarder.technicalassistant.services.database.mysql.names;

import ru.greatlarder.technicalassistant.domain.Names;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetNames;
import ru.greatlarder.technicalassistant.services.database.general.GetNamesService;
import ru.greatlarder.technicalassistant.services.database.general.GetNamesServiceImpl;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.NamesTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.NamesTableMySQLImpl;

import java.sql.SQLException;

public class GetNamesSeatingArrangementsByNameMySQL implements GetNames {
    @Override
    public Names getNames(User user, String nameCompany, String nameSeating) {
        ConnectMySQL connection = new ConnectMySQL(user);
        connection.createNamesTableMySQL();
        NamesTableMySQL namesTableMySQL = new NamesTableMySQLImpl();

        GetNamesService getNamesService = new GetNamesServiceImpl();

        try {
            connection.resultSetMySQL = connection.statementMySQL.executeQuery(namesTableMySQL.SELECT(user.getNameDB()));
            while (connection.resultSetMySQL.next()){
                if(connection.resultSetMySQL.getString("nameCompany").equals(nameCompany)
                        && connection.resultSetMySQL.getString("names").equals(nameSeating)){
                    return getNamesService.getNames(connection.resultSetMySQL);
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
