package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.PhoneBook;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetPhoneBookServiceImpl implements GetPhoneBookService{
    @Override
    public PhoneBook getPhoneBook(ResultSet resultSet) throws SQLException {
        PhoneBook contactCart = new PhoneBook();
        contactCart.setId(resultSet.getInt("id"));
        contactCart.setLastName(resultSet.getString("lastName"));
        contactCart.setFirstName(resultSet.getString("firstName"));
        contactCart.setMail(resultSet.getString("mail"));
        contactCart.setPhone(resultSet.getString("phone"));
        contactCart.setIdUser(resultSet.getInt("user"));
        contactCart.setImgPath(resultSet.getString("imgPatch"));
        return contactCart;
    }
}
