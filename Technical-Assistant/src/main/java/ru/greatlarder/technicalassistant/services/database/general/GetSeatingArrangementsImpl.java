package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetSeatingArrangementsImpl implements GetSeatingsArrangementsService{
    @Override
    public SeatingArrangements getSeating(ResultSet resultSet) throws SQLException {
        SeatingArrangements seatingArrangements = new SeatingArrangements();

        seatingArrangements.setId(resultSet.getInt("id"));
        seatingArrangements.setNameSeatingArrangements(resultSet.getString("nameSeatingArrangements"));
        seatingArrangements.setNameCompany(resultSet.getString("nameCompany"));
        seatingArrangements.setUrlImageSeatingArrangements(resultSet.getString("urlImageSeatingArrangements"));
        seatingArrangements.setNumberOfParticipants(resultSet.getInt("numberOfParticipants"));

        return seatingArrangements;
    }
}
