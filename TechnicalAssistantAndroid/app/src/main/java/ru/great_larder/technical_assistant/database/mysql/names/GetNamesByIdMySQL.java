package ru.great_larder.technical_assistant.database.mysql.names;

import java.sql.SQLException;
import java.util.Objects;

import ru.great_larder.technical_assistant.database.GetNames;
import ru.great_larder.technical_assistant.database.general.GetNamesService;
import ru.great_larder.technical_assistant.database.general.GetNamesServiceImpl;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.NamesTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.NamesTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.Names;
import ru.great_larder.technical_assistant.domain.user.User;

public class GetNamesByIdMySQL implements GetNames {
    @Override
    public Names getNames(User user, String nameCompany, String id) {
        ConnectMySQL connection = new ConnectMySQL(user);
        connection.createNamesTableMySQL();
        NamesTableMySQL namesTableMySQL = new NamesTableMySQLImpl();

        GetNamesService getNamesService = new GetNamesServiceImpl();

        try {
            connection.resultSetMySQL = connection.statementMySQL.executeQuery(namesTableMySQL.SELECT(user.getNameDB()));
            while (connection.resultSetMySQL.next()){
                if(Objects.equals(connection.resultSetMySQL.getInt("id"), Integer.valueOf(id))){
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
