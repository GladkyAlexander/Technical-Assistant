package ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.impl;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.ListSeatingArrangementsNameRepositoryMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.ListSeatingArrangementsNameTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.ListSeatingArrangementsNameTableMySQLImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListSeatingArrangementsNameRepositoryMySQLImpl implements ListSeatingArrangementsNameRepositoryMySQL {
    User user;
    ConnectMySQL connectMySQL;
    @Override
    public void setSeatingArrangementsName(SeatingArrangements seatingArrangementsName) {

    }

    @Override
    public void setSeatingArrangementsNameList(User user, List<SeatingArrangements> seatingArrangements) {
        this.user = user;
        ConnectMySQL connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createListSeatingArrangementsNameTableMySQL();
        ListSeatingArrangementsNameTableMySQL listSeatingArrangementsNameTableMySQL = new ListSeatingArrangementsNameTableMySQLImpl();

        try {
            PreparedStatement ps = connectMySQL.connectionMySQL.prepareStatement(listSeatingArrangementsNameTableMySQL.INSERT(this.user.getNameDB()));

            for (SeatingArrangements seatingArrangements1 : seatingArrangements ){
                ps.setInt(1,seatingArrangements1.getId());
                ps.setString(2, seatingArrangements1.getNameSeatingArrangements());
                ps.setString(3, seatingArrangements1.getNameCompany());
                ps.executeUpdate();
            }
            connectMySQL.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQL_DB();
        }
    }

    @Override
    public List<String> getSeatingArrangementsNameList(User user, String companyName) {
        this.user = user;
        List<SeatingArrangements> eventsList = new ArrayList<SeatingArrangements>();
        List<String> result = new ArrayList<String>();
        connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createSeatingArrangementsTableMySQL();
        ListSeatingArrangementsNameTableMySQL listSeatingArrangementsNameTableMySQL = new ListSeatingArrangementsNameTableMySQLImpl();

        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(listSeatingArrangementsNameTableMySQL.SELECT(this.user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()){
                if (companyName.equals(connectMySQL.resultSetMySQL.getString("nameCompany"))){
                    eventsList.add(getSeating(connectMySQL.resultSetMySQL));
                }
            }
            connectMySQL.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQL_DB();
        }

        for(SeatingArrangements events : eventsList){
            result.add(events.getNameSeatingArrangements());
        }
        return result;
    }

    private SeatingArrangements getSeating(ResultSet resultSetMySQL) throws SQLException {
        SeatingArrangements seatingArrangements = new SeatingArrangements();
        seatingArrangements.setNameSeatingArrangements(resultSetMySQL.getString("seatingArrangements"));
        seatingArrangements.setNameCompany(resultSetMySQL.getString("nameCompany"));
        return seatingArrangements;
    }

    @Override
    public SeatingArrangements getSeatingArrangementsName(String companyName, String SeatingArrangements) {
        return null;
    }

    @Override
    public void updateSeatingArrangementsName(SeatingArrangements seatingArrangements) {

    }

    @Override
    public void updateSeatingArrangementsNameList(List<SeatingArrangements> seatingArrangements) {

    }
}
