package ru.great_larder.technical_assistant.database.mysql.sintax;

public interface NamesTableMySQL {
    String CREATE(String nameDB);
    String INSERT(String nameDB);
    String UPDATE(String nameDB);
    String SELECT(String nameDB);
}