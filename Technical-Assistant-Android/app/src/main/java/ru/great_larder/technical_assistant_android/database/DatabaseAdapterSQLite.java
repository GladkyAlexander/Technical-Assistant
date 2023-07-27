package ru.great_larder.technical_assistant_android.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ru.great_larder.technical_assistant_android.domain.user.Engineer;
import ru.great_larder.technical_assistant_android.domain.user.Reception;
import ru.great_larder.technical_assistant_android.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapterSQLite {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    public DatabaseAdapterSQLite(Context context){
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }
    public DatabaseAdapterSQLite open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }
    private Cursor getAllEntries(){
        String[] columns = new String[] {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_LAST_NAME, DatabaseHelper.COLUMN_FIRST_NAME
            , DatabaseHelper.COLUMN_MAIL_ADDRESS, DatabaseHelper.COLUMN_PHONE, DatabaseHelper.COLUMN_POST
        , DatabaseHelper.COLUMN_COMPANY_AFFILIATION, DatabaseHelper.COLUMN_LANGUAGE, DatabaseHelper.COLUMN_LOGIN
        , DatabaseHelper.COLUMN_PASSWORD, DatabaseHelper.COLUMN_SERVER, DatabaseHelper.COLUMN_PORT
        , DatabaseHelper.COLUMN_NAME_BD, DatabaseHelper.COLUMN_USER_DB, DatabaseHelper.COLUMN_PASSWORD_DB
        , DatabaseHelper.COLUMN_SERVER_FTP, DatabaseHelper.COLUMN_PORT_FTP, DatabaseHelper.COLUMN_USER_FTP
            , DatabaseHelper.COLUMN_PASSWORD_FTP};
        return  database.query(DatabaseHelper.TABLE_USERS, columns, null, null, null, null, null);
    }
    @SuppressLint("Range")
    public List<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()){
            if(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POST)).equals("Engineer")
                || cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POST)).equals("Инженер")){
                Engineer engineer = new Engineer();
                engineer.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                engineer.setLastName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LAST_NAME)));
                engineer.setFirstName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FIRST_NAME)));
                engineer.setPost(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POST)));
                engineer.setCompanyAffiliation(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COMPANY_AFFILIATION)));
                engineer.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE)));
                engineer.setMailAddress(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MAIL_ADDRESS)));
                engineer.setLanguage(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LANGUAGE)));
                engineer.setLogin(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LOGIN)));
                engineer.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD)));
                engineer.setServer(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SERVER)));
                engineer.setPort(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PORT)));
                engineer.setNameDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_BD)));
                engineer.setUserDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_DB)));
                engineer.setPasswordDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD_DB)));
                engineer.setServerFTP(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SERVER_FTP)));
                engineer.setPortFTP(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_SERVER_FTP)));
                engineer.setUserFTP(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_FTP)));
                engineer.setPasswordFTP(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD_FTP)));
                users.add(engineer);
            }
            if(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POST)).equals("Engineer")
                || cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POST)).equals("Инженер")){
                Reception reception = new Reception();
                reception.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                reception.setLastName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LAST_NAME)));
                reception.setFirstName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FIRST_NAME)));
                reception.setPost(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POST)));
                reception.setCompanyAffiliation(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COMPANY_AFFILIATION)));
                reception.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE)));
                reception.setMailAddress(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MAIL_ADDRESS)));
                reception.setLanguage(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LANGUAGE)));
                reception.setLogin(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LOGIN)));
                reception.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD)));
                reception.setServer(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SERVER)));
                reception.setPort(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PORT)));
                reception.setNameDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_BD)));
                reception.setUserDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_DB)));
                reception.setPasswordDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD_DB)));
                reception.setServerFTP(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SERVER_FTP)));
                reception.setPortFTP(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_SERVER_FTP)));
                reception.setUserFTP(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_FTP)));
                reception.setPasswordFTP(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD_FTP)));
                users.add(reception);
            }
        }
        cursor.close();
        return  users;
    }
    @SuppressLint("Range")
    public User getUser(String login, String password){
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()){
            if(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LOGIN)).equals(login)
                && cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD)).equals(password)){
                if(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POST)).equals("Engineer")
                    || cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POST)).equals("Инженер")){
                    Engineer engineer = new Engineer();
                    engineer.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                    engineer.setLastName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LAST_NAME)));
                    engineer.setFirstName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FIRST_NAME)));
                    engineer.setPost(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POST)));
                    engineer.setCompanyAffiliation(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COMPANY_AFFILIATION)));
                    engineer.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE)));
                    engineer.setMailAddress(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MAIL_ADDRESS)));
                    engineer.setLanguage(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LANGUAGE)));
                    engineer.setLogin(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LOGIN)));
                    engineer.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD)));
                    engineer.setServer(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SERVER)));
                    engineer.setPort(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PORT)));
                    engineer.setNameDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_BD)));
                    engineer.setUserDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_DB)));
                    engineer.setPasswordDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD_DB)));
                    engineer.setServerFTP(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SERVER_FTP)));
                    engineer.setPortFTP(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_SERVER_FTP)));
                    engineer.setUserFTP(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_FTP)));
                    engineer.setPasswordFTP(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD_FTP)));
                    return engineer;
                }
                if(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POST)).equals("Reception Secretary")
                    || cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POST)).equals("Секретарь приемной")){
                    Reception reception = new Reception();
                    reception.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                    reception.setLastName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LAST_NAME)));
                    reception.setFirstName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FIRST_NAME)));
                    reception.setPost(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POST)));
                    reception.setCompanyAffiliation(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COMPANY_AFFILIATION)));
                    reception.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE)));
                    reception.setMailAddress(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MAIL_ADDRESS)));
                    reception.setLanguage(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LANGUAGE)));
                    reception.setLogin(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LOGIN)));
                    reception.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD)));
                    reception.setServer(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SERVER)));
                    reception.setPort(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PORT)));
                    reception.setNameDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_BD)));
                    reception.setUserDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_DB)));
                    reception.setPasswordDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD_DB)));
                    reception.setServerFTP(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SERVER_FTP)));
                    reception.setPortFTP(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_SERVER_FTP)));
                    reception.setUserFTP(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_FTP)));
                    reception.setPasswordFTP(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD_FTP)));
                    return reception;
                }
            }
           
        }
        cursor.close();
        return null;
    }
    public Integer setUser(User user){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_LAST_NAME, user.getLastName());
        cv.put(DatabaseHelper.COLUMN_FIRST_NAME, user.getFirstName());
        cv.put(DatabaseHelper.COLUMN_POST, user.getPost());
        cv.put(DatabaseHelper.COLUMN_COMPANY_AFFILIATION, user.getCompanyAffiliation());
        cv.put(DatabaseHelper.COLUMN_PHONE, user.getPhone());
        cv.put(DatabaseHelper.COLUMN_MAIL_ADDRESS, user.getMailAddress());
        cv.put(DatabaseHelper.COLUMN_LANGUAGE, user.getLanguage());
        cv.put(DatabaseHelper.COLUMN_LOGIN, user.getLogin());
        cv.put(DatabaseHelper.COLUMN_PASSWORD, user.getPassword());
        cv.put(DatabaseHelper.COLUMN_SERVER, user.getServer());
        cv.put(DatabaseHelper.COLUMN_PORT, user.getPort());
        cv.put(DatabaseHelper.COLUMN_NAME_BD, user.getNameDB());
        cv.put(DatabaseHelper.COLUMN_USER_DB, user.getUserDB());
        cv.put(DatabaseHelper.COLUMN_PASSWORD_DB, user.getPasswordDB());
        cv.put(DatabaseHelper.COLUMN_SERVER_FTP, user.getServerFTP());
        cv.put(DatabaseHelper.COLUMN_PORT_FTP, user.getPortFTP());
        cv.put(DatabaseHelper.COLUMN_USER_FTP, user.getUserFTP());
        cv.put(DatabaseHelper.COLUMN_PASSWORD_FTP, user.getPasswordFTP());
        return Math.toIntExact(database.insert(DatabaseHelper.TABLE_USERS, null, cv));
    }
    public Integer update(User user){
        
        String whereClause = DatabaseHelper.COLUMN_ID + "=" + user.getId();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_LAST_NAME, user.getLastName());
        cv.put(DatabaseHelper.COLUMN_FIRST_NAME, user.getFirstName());
        cv.put(DatabaseHelper.COLUMN_POST, user.getPost());
        cv.put(DatabaseHelper.COLUMN_COMPANY_AFFILIATION, user.getCompanyAffiliation());
        cv.put(DatabaseHelper.COLUMN_PHONE, user.getPhone());
        cv.put(DatabaseHelper.COLUMN_MAIL_ADDRESS, user.getMailAddress());
        cv.put(DatabaseHelper.COLUMN_LANGUAGE, user.getLanguage());
        cv.put(DatabaseHelper.COLUMN_LOGIN, user.getLogin());
        cv.put(DatabaseHelper.COLUMN_PASSWORD, user.getPassword());
        cv.put(DatabaseHelper.COLUMN_SERVER, user.getServer());
        cv.put(DatabaseHelper.COLUMN_PORT, user.getPort());
        cv.put(DatabaseHelper.COLUMN_NAME_BD, user.getNameDB());
        cv.put(DatabaseHelper.COLUMN_USER_DB, user.getUserDB());
        cv.put(DatabaseHelper.COLUMN_PASSWORD_DB, user.getPasswordDB());
        cv.put(DatabaseHelper.COLUMN_SERVER_FTP, user.getServerFTP());
        cv.put(DatabaseHelper.COLUMN_PORT_FTP, user.getPortFTP());
        cv.put(DatabaseHelper.COLUMN_USER_FTP, user.getUserFTP());
        cv.put(DatabaseHelper.COLUMN_PASSWORD_FTP, user.getPasswordFTP());
        
        return database.update(DatabaseHelper.TABLE_USERS, cv, whereClause, null);
    }
}
