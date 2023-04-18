package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.user.Engineer;
import ru.greatlarder.technicalassistant.domain.user.Reception;
import ru.greatlarder.technicalassistant.domain.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserServiceImpl implements GetUserService {
    @Override
    public User getUser(ResultSet resultSet) {
        try {
            if(resultSet.getString("post").equals("Engineer")
                    || resultSet.getString("post").equals("Инженер")) {
                Engineer engineer = new Engineer();
                engineer.setId(resultSet.getInt("id"));
                engineer.setLastName(resultSet.getString("lastName"));
                engineer.setFirstName(resultSet.getString("firstName"));
                engineer.setMailAddress(resultSet.getString("mailAddress"));
                engineer.setPhone(resultSet.getString("phone"));
                engineer.setPost(resultSet.getString("post"));
                engineer.setCompanyAffiliation(resultSet.getString("companyAffiliation"));
                engineer.setLanguage(resultSet.getString("language"));
                engineer.setLogin(resultSet.getString("login"));
                engineer.setPassword(resultSet.getString("password"));
                engineer.setServer(resultSet.getString("server"));
                engineer.setPort(resultSet.getString("port"));
                engineer.setNameDB(resultSet.getString("nameDB"));
                engineer.setUserDB(resultSet.getString("userDB"));
                engineer.setPasswordDB(resultSet.getString("passwordDB"));
                engineer.setServerFTP(resultSet.getString("serverFTP"));
                engineer.setPortFTP(resultSet.getInt("portFTP"));
                engineer.setUserFTP(resultSet.getString("userFTP"));
                engineer.setPasswordFTP(resultSet.getString("passwordFTP"));
                return engineer;
            }
            if(resultSet.getString("post").equals("Reception Secretary")
                    || resultSet.getString("post").equals("Секретарь приемной")){
                Reception reception = new Reception();
                reception.setId(resultSet.getInt("id"));
                reception.setLastName(resultSet.getString("lastName"));
                reception.setFirstName(resultSet.getString("firstName"));
                reception.setMailAddress(resultSet.getString("mailAddress"));
                reception.setPhone(resultSet.getString("phone"));
                reception.setPost(resultSet.getString("post"));
                reception.setCompanyAffiliation(resultSet.getString("companyAffiliation"));
                reception.setLanguage(resultSet.getString("language"));
                reception.setLogin(resultSet.getString("login"));
                reception.setPassword(resultSet.getString("password"));
                reception.setServer(resultSet.getString("server"));
                reception.setPort(resultSet.getString("port"));
                reception.setNameDB(resultSet.getString("nameDB"));
                reception.setUserDB(resultSet.getString("userDB"));
                reception.setPasswordDB(resultSet.getString("passwordDB"));
                reception.setServerFTP(resultSet.getString("serverFTP"));
                reception.setPortFTP(resultSet.getInt("portFTP"));
                reception.setUserFTP(resultSet.getString("userFTP"));
                reception.setPasswordFTP(resultSet.getString("passwordFTP"));
                return reception;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
