package ru.great_larder.technical_assistant.database.mysql.names;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.great_larder.technical_assistant.database.GetListNames;
import ru.great_larder.technical_assistant.database.general.GetNamesService;
import ru.great_larder.technical_assistant.database.general.GetNamesServiceImpl;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.NamesTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.NamesTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.Names;
import ru.great_larder.technical_assistant.domain.user.User;

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
