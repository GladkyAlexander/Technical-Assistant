package ru.great_larder.technical_assistant.database.general;


import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.PhoneBook;

public interface GetPhoneBookService {
    PhoneBook getPhoneBook(ResultSet resultSet) throws SQLException;
}
