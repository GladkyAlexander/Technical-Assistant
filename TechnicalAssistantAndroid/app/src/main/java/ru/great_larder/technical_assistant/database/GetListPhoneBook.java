package ru.great_larder.technical_assistant.database;

import java.util.List;

import ru.great_larder.technical_assistant.domain.PhoneBook;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetListPhoneBook {
    List<PhoneBook> getListPhoneBook(User user, String nameCompany, String value);
}
