package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.PhoneBook;
import ru.great_larder.technical_assistant.domain.user.User;

public interface UpdatePhoneBook {
    void updatePhoneBook(User user, String nameCompany, PhoneBook phoneBook, Integer id);
}
