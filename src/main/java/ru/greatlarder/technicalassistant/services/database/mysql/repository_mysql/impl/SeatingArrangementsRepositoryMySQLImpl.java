package ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.impl;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.SeatingArrangementsRepositoryMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.SeatingArrangementsTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.SeatingArrangementsTableMySQLImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SeatingArrangementsRepositoryMySQLImpl implements SeatingArrangementsRepositoryMySQL {

    private User user;
    @Override
    public List<SeatingArrangements> getSeatingArrangementsList(User user, String nameCompany) {
        return null;
    }

    @Override
    public SeatingArrangements getSeatingArrangementsByName(User user, Integer id) {
        this.user= user;

        ConnectMySQL connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createSeatingArrangementsTableMySQL();
        SeatingArrangementsTableMySQL seatingArrangements = new SeatingArrangementsTableMySQLImpl();

        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(seatingArrangements.SELECT(this.user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()){
                if(connectMySQL.resultSetMySQL.getInt("id") == id){
                    return getSeating(connectMySQL.resultSetMySQL);
                }
            }
        connectMySQL.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQL_DB();
        }
        return null;
    }

    private SeatingArrangements getSeating(ResultSet resultSetMySQL) throws SQLException {
        SeatingArrangements result = new SeatingArrangements();
        result.setId(resultSetMySQL.getInt("id"));
        result.setNameSeatingArrangements(resultSetMySQL.getString("nameSeatingArrangements"));
        result.setNameCompany(resultSetMySQL.getString("nameCompany"));
        result.setUrlImageSeatingArrangements(resultSetMySQL.getString("urlImageSeatingArrangements"));
        result.setNumberOfParticipants(resultSetMySQL.getInt("numberOfParticipants"));
        return result;
    }

    @Override
    public void setSeatingArrangements(SeatingArrangements seatingArrangements) {

    }

    @Override
    public void changeSeatingArrangements(SeatingArrangements seatingArrangements) {

    }
}
