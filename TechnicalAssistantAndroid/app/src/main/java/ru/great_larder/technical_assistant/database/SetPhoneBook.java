package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.PhoneBook;
import ru.great_larder.technical_assistant.domain.user.User;

public interface SetPhoneBook {
    Integer setPhoneBook(User user, String nameCompany, PhoneBook phoneBook);
}
