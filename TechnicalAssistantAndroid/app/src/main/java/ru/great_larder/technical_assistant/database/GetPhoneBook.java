package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.PhoneBook;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetPhoneBook {
    PhoneBook getPhoneBook(User user, String nameCompany, String value);
}
