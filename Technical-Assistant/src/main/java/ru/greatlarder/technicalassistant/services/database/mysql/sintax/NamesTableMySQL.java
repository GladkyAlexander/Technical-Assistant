package ru.greatlarder.technicalassistant.services.database.mysql.sintax;

public interface NamesTableMySQL {
    String CREATE(String nameDB);
    String INSERT(String nameDB);
    String UPDATE(String nameDB);
    String SELECT(String nameDB);
}
