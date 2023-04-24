package ru.great_larder.technical_assistant.database.mysql.seating_arrangements;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.database.UpdateSeatingArrangements;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.SeatingArrangementsTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.SeatingArrangements;
import ru.great_larder.technical_assistant.domain.user.User;

public class UpdateSeatingArrangementsMySQL implements UpdateSeatingArrangements {
    @Override
    public void updateSeatingArrangements(User user, String nameCompany, SeatingArrangements seatingArrangements, Integer id) {
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createSeatingArrangementsTableMySQL();
        SeatingArrangementsTableMySQLImpl seatingArrangementsTableMySQL = new SeatingArrangementsTableMySQLImpl();
        try {
            PreparedStatement cf = connectMySQL.connectionMySQL.prepareStatement(seatingArrangementsTableMySQL.UPDATE(user.getNameDB()));

            cf.setInt(1, seatingArrangements.getId());
            cf.setString(2, seatingArrangements.getNameSeatingArrangements());
            cf.setString(3, seatingArrangements.getNameCompany());
            cf.setString(4, seatingArrangements.getUrlImageSeatingArrangements());
            cf.setInt(5, seatingArrangements.getNumberOfParticipants());

            cf.setInt(6, id);

            cf.executeUpdate();
            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }
    }
}
