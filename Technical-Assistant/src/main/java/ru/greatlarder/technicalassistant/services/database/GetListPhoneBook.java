package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.PhoneBook;
import ru.greatlarder.technicalassistant.domain.user.User;

import java.util.List;

public interface GetListPhoneBook {
    List<PhoneBook> getListPhoneBook(User user, String nameCompany, String value);
}
