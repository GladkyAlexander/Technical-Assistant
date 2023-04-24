package ru.great_larder.technical_assistant.database.general;

import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.SeatingArrangements;

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
