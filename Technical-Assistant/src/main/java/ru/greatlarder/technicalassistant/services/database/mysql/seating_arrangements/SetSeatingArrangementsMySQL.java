package ru.greatlarder.technicalassistant.services.database.mysql.seating_arrangements;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.SeatingArrangementsTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.SeatingArrangementsTableMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.SetSeatingArrangements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
