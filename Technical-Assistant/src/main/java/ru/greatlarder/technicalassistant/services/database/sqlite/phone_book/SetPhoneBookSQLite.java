package ru.greatlarder.technicalassistant.services.database.sqlite.phone_book;

import ru.greatlarder.technicalassistant.domain.PhoneBook;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.SetPhoneBook;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteCartContact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class SetPhoneBookSQLite implements SetPhoneBook {
    @Override
    public Integer setPhoneBook(User user, String nameCompany, PhoneBook phoneBook) {
        createCartContact();
        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteCartContact.INSERT_CART_CONTACT, Statement.RETURN_GENERATED_KEYS);
            cf.setString(1, phoneBook.getLastName());
            cf.setString(2, phoneBook.getFirstName());
            cf.setString(3, phoneBook.getMail());
            cf.setString(4, phoneBook.getPhone());
            cf.setInt(5, phoneBook.getIdUser());
            cf.setString(6, phoneBook.getImgPath());
            if(cf.executeUpdate()>0){
                ResultSet rs = cf.getGeneratedKeys();
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeDB();
        }
        return null;
    }
}
