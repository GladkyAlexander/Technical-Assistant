package ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.impl;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.SeatingArrangementsRepositoryMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.SeatingArrangementsTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.SeatingArrangementsTableMySQLImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SeatingArrangementsRepositoryMySQLImpl implements SeatingArrangementsRepositoryMySQL {

    private User user;
    @Override
    public List<SeatingArrangements> getSeatingArrangementsList(User user, String nameCompany) {
        return null;
    }

    @Override
    public SeatingArrangements getSeatingArrangementsById(User user, Integer id) {
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

    @Override
    public SeatingArrangements getSeatingArrangementsByName(User user, String name) {
        this.user= user;

        ConnectMySQL connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createSeatingArrangementsTableMySQL();
        SeatingArrangementsTableMySQL seatingArrangements = new SeatingArrangementsTableMySQLImpl();

        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(seatingArrangements.SELECT(this.user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()){
                if(connectMySQL.resultSetMySQL.getString("nameSeatingArrangements").equals(name)){
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
    public Integer setSeatingArrangements(User user, SeatingArrangements seatingArrangements) {
        this.user = user;
        ConnectMySQL connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createSeatingArrangementsTableMySQL();

        SeatingArrangementsTableMySQL seatingArrangementsTableMySQL = new SeatingArrangementsTableMySQLImpl();

        try {
            PreparedStatement ps = connectMySQL.connectionMySQL.prepareStatement(seatingArrangementsTableMySQL.INSERT(this.user.getNameDB()), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, seatingArrangements.getNameSeatingArrangements());
            ps.setString(2, seatingArrangements.getNameCompany());
            if(seatingArrangements.getUrlImageSeatingArrangements() != null) {
                ps.setString(3, seatingArrangements.getUrlImageSeatingArrangements());
            } else ps.setString(3, null);
            ps.setInt(4, seatingArrangements.getNumberOfParticipants());

            if(ps.executeUpdate() > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void changeSeatingArrangements(SeatingArrangements seatingArrangements) {

    }
}
