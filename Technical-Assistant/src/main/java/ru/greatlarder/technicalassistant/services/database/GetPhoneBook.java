package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.PhoneBook;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface GetPhoneBook {
    PhoneBook getPhoneBook(User user, String nameCompany, String value);
}
