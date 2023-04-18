package ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite;

public interface SQLiteEvents {
    String CREATE_EVENTS = "CREATE TABLE if not exists 'events'(" +
            "  'id' INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", 'nameEvent' text" +
            ", 'urlImageEvent' text" +
            ", 'idSeatingArrangements' int" +
            ", 'lastNameCustomer' text" +
            ", 'firstNameCustomer' text" +
            ", 'idDay' int" +
            ", 'eventStartTime' text" +
            ", 'endTimeOfTheEvent' text" +
            ", 'note' text" +
            ", 'nameCompany' text);";

    String INSERT_EVENTS = "INSERT INTO events (" +
            " nameEvent," +
            " urlImageEvent," +
            " idSeatingArrangements," +
            " lastNameCustomer," +
            " firstNameCustomer," +
            " idDay," +
            " eventStartTime," +
            " endTimeOfTheEvent," +
            " note," +
            " nameCompany)" +
            " VALUES (?,?,?,?,?,?,?,?,?,?)";

    String UPDATE_EVENTS = "UPDATE 'events' SET " +
            " id = ?," +
            " nameEvent = ?," +
            " urlImageEvent = ?," +
            " idSeatingArrangements = ?," +
            " lastNameCustomer = ?," +
            " firstNameCustomer = ?," +
            " idDay = ?," +
            " eventStartTime = ?," +
            " endTimeOfTheEvent = ?," +
            " note = ?," +
            " nameCompany = ? WHERE id = ?";

    String READ_EVENTS = "SELECT * FROM events";
}
