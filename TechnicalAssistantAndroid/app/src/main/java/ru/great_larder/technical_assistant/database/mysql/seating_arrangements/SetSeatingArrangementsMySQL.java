package ru.great_larder.technical_assistant.database.mysql.seating_arrangements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ru.great_larder.technical_assistant.database.SetSeatingArrangements;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.SeatingArrangementsTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.SeatingArrangementsTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.SeatingArrangements;
import ru.great_larder.technical_assistant.domain.user.User;

public class SetSeatingArrangementsMySQL implements SetSeatingArrangements {
    @Override
    public Integer setSeatingArrangements(User user, String nameCompany, SeatingArrangements seatingArrangements) {
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createSeatingArrangementsTableMySQL();

        SeatingArrangementsTableMySQL seatingArrangementsTableMySQL = new SeatingArrangementsTableMySQLImpl();

        try {
            PreparedStatement ps = connectMySQL.connectionMySQL.prepareStatement(seatingArrangementsTableMySQL.INSERT(user.getNameDB()), Statement.RETURN_GENERATED_KEYS);

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
            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }
        return null;
    }
}
