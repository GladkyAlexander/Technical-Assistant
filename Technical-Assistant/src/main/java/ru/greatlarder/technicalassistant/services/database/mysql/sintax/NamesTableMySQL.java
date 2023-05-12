package ru.greatlarder.technicalassistant.services.database.mysql.sintax;

public interface NamesTableMySQL {
    String CREATE(String nameDB);
    String INSERT(String nameDB);
    String UPDATE_BY_NAME(String nameDB);
    String UPDATE_BY_ID(String nameDB);
    String SELECT(String nameDB);
}
