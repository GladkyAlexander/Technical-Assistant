package ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite;

public interface SQLiteDefect {
    String CREATE_DEFECT = "CREATE TABLE if not exists 'defect'(" +
            " 'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
            " 'defect' text," +
            " 'room' text," +
            " 'date_defect' date," +
            " 'time_defect' text," +
            " 'initiatorName' text," +
            " 'condition' text," +
            " 'dateOfCompletion' date," +
            " 'timeOfCompletion' text," +
            " 'executorName' text," +
            " 'serial_number_equipment' text," +
            " 'idEquipment' INTEGER," +
            " 'causeOfTheMalfunction' text," +
            " 'howFixed' text," +
            " 'noteExecutor' text," +
            " 'nameCompany' text);";

    String INSERT_DEFECT = "INSERT INTO defect (" +
            " defect," +
            " room," +
            " date_defect," +
            " time_defect," +
            " initiatorName," +
            " condition," +
            " dateOfCompletion," +
            " timeOfCompletion," +
            " executorName," +
            " serial_number_equipment," +
            " idEquipment," +
            " causeOfTheMalfunction," +
            " howFixed," +
            " noteExecutor," +
            " nameCompany)" +
            " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    String UPDATE_DEFECT = "UPDATE defect SET " +
            " condition = ?," +
            " dateOfCompletion = ?," +
            " timeOfCompletion = ?," +
            " executorName = ?," +
            " causeOfTheMalfunction = ?," +
            " howFixed = ?," +
            " noteExecutor = ? WHERE id = ?";

    String READ_DEFECT = "SELECT * FROM defect";
}
