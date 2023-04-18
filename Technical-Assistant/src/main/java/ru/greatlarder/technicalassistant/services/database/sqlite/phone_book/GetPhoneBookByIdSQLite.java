package ru.greatlarder.technicalassistant.services.database.sqlite.phone_book;

import ru.greatlarder.technicalassistant.domain.PhoneBook;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetPhoneBook;
import ru.greatlarder.technicalassistant.services.database.general.GetPhoneBookService;
import ru.greatlarder.technicalassistant.services.database.general.GetPhoneBookServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteCartContact;

import java.sql.SQLException;
import java.util.Objects;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class GetPhoneBookByIdSQLite implements GetPhoneBook {
    @Override
    public PhoneBook getPhoneBook(User user, String nameCompany, String id) {
        createCartContact();
        GetPhoneBookService getPhoneBookService = new GetPhoneBookServiceImpl();
        try {
            resultSet = statement.executeQuery(SQLiteCartContact.READE_CART_CONTACT);
            while (resultSet.next()){
                if(Objects.equals(resultSet.getInt("id"), Integer.valueOf(id))){
                    return getPhoneBookService.getPhoneBook(resultSet);
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null;
    }
}
