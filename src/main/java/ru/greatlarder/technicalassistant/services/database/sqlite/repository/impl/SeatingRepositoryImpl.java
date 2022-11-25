package ru.greatlarder.technicalassistant.services.database.sqlite.repository.impl;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository.SeatingRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteSeatingArrangements;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class SeatingRepositoryImpl implements SeatingRepository {

    @Override
    public List<SeatingArrangements> getListSeatingArrangementsForCompany(String nameCompany) {
        List<SeatingArrangements> listSeatingArrangements = new ArrayList<SeatingArrangements>();
        createSettingArrangementTable();
        try {
            resultSet = statement.executeQuery(SQLiteSeatingArrangements.READ_SEATING_ARRANGEMENTS);
            while (resultSet.next()){
                if(resultSet.getString("nameCompany").equals(nameCompany)){
                    SeatingArrangements seatingArrangements = new SeatingArrangements();
                    seatingArrangements.setId(resultSet.getInt("id"));
                    seatingArrangements.setNameSeatingArrangements(resultSet.getString("nameSeatingArrangements"));
                    seatingArrangements.setNameCompany(resultSet.getString("nameCompany"));
                    listSeatingArrangements.add(seatingArrangements);
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return listSeatingArrangements;
    }

    @Override
    public void setSeatingArrangements(SeatingArrangements seatingArrangements) {
        createSettingArrangementTable();
        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteSeatingArrangements.INSERT_SEATING_ARRANGEMENTS);

            cf.setString(1, seatingArrangements.getNameSeatingArrangements());
            cf.setString(2, seatingArrangements.getNameCompany());
            cf.setString(3, seatingArrangements.getUrlImageSeatingArrangements());

            cf.executeUpdate();
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }

    @Override
    public SeatingArrangements getSeatingArrangementsForName(String nameCompany, String nameSeatingArrangements) {
        SeatingArrangements seatingArrangements = new SeatingArrangements();
        createRoomTable();

        try {
            resultSet = statement.executeQuery(SQLiteSeatingArrangements.READ_SEATING_ARRANGEMENTS);

            while (resultSet.next()){
                if(resultSet.getString("nameSeatingArrangements").equals(nameSeatingArrangements)
                && resultSet.getString("nameCompany").equals(nameCompany)){
                    seatingArrangements.setId(resultSet.getInt("id"));
                    seatingArrangements.setNameSeatingArrangements(resultSet.getString("nameSeatingArrangements"));
                    seatingArrangements.setNameCompany(resultSet.getString("nameCompany"));
                }
            }
closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return seatingArrangements;
    }

    @Override
    public void changeSeatingArrangementsName(SeatingArrangements seatingArrangements, String value, String column) {
        createSettingArrangementTable();
        String scl = "UPDATE seating_arrangements SET " + column + " = ? WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(scl);
            ps.setString(1, value);
            ps.setInt(2, seatingArrangements.getId());

            ps.executeUpdate();
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }
}
