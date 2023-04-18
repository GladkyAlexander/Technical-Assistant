package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.PhoneBook;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GetPhoneBookService {
    PhoneBook getPhoneBook(ResultSet resultSet) throws SQLException;
}
