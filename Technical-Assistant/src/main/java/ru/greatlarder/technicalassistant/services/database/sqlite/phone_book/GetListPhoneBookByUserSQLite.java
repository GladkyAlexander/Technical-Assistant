package ru.greatlarder.technicalassistant.services.database.sqlite.phone_book;

import ru.greatlarder.technicalassistant.domain.PhoneBook;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListPhoneBook;
import ru.greatlarder.technicalassistant.services.database.general.GetPhoneBookService;
import ru.greatlarder.technicalassistant.services.database.general.GetPhoneBookServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteCartContact;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class GetListPhoneBookByUserSQLite implements GetListPhoneBook {
    @Override
    public List<PhoneBook> getListPhoneBook(User user, String nameCompany, String idUser) {
        List<PhoneBook> phoneBookList = new ArrayList<>();
        createCartContact();
        GetPhoneBookService getPhoneBookService = new GetPhoneBookServiceImpl();
        try {
            resultSet = statement.executeQuery(SQLiteCartContact.READE_CART_CONTACT);

            while (resultSet.next()){
                if(Objects.equals(resultSet.getInt("user"), Integer.valueOf(idUser))){
                    phoneBookList.add(getPhoneBookService.getPhoneBook(resultSet));
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return phoneBookList;
    }
}
