package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.PhoneBook;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface SetPhoneBook {
    Integer setPhoneBook(User user, String nameCompany, PhoneBook phoneBook);
}
