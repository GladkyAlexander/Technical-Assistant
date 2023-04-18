package ru.greatlarder.technicalassistant.services.database.mysql.names;

import ru.greatlarder.technicalassistant.domain.Names;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListNames;
import ru.greatlarder.technicalassistant.services.database.general.GetNamesService;
import ru.greatlarder.technicalassistant.services.database.general.GetNamesServiceImpl;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.NamesTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.NamesTableMySQLImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListNamesByCompanyNameMySQL implements GetListNames {
    @Override
    public List<Names> getListNames(User user, String nameCompany, String value) {
        List<Names> names = new ArrayList<>();
        ConnectMySQL connection = new ConnectMySQL(user);
        connection.createNamesTableMySQL();
        NamesTableMySQL namesTableMySQL = new NamesTableMySQLImpl();
        GetNamesService getNames = new GetNamesServiceImpl();
        try {
            connection.resultSetMySQL = connection.statementMySQL.executeQuery(namesTableMySQL.SELECT(user.getNameDB()));
            while (connection.resultSetMySQL.next()){
                if(connection.resultSetMySQL.getString("nameCompany").equals(nameCompany)){
                    names.add(getNames.getNames(connection.resultSetMySQL));
                }
            }
            connection.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeMySQLDatabase();
        }
        return names;
    }
}
