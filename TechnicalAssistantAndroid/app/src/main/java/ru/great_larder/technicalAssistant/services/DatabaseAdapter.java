package ru.great_larder.technicalAssistant.services;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import ru.great_larder.technicalAssistant.domain.Engineer;
import ru.great_larder.technicalAssistant.domain.Reception;
import ru.great_larder.technicalAssistant.domain.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    
    public DatabaseAdapter(Context context){
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }
    
    public DatabaseAdapter open(){
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
        , DatabaseHelper.COLUMN_NAME_DB, DatabaseHelper.COLUMN_USER_DB, DatabaseHelper.COLUMN_PASSWORD_DB};
        return  database.query(DatabaseHelper.TABLE_USERS, columns, null, null, null, null, null);
    }
    
    @SuppressLint("Range")
    public List<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = getAllEntries();
        User user = new User();
        while (cursor.moveToNext()){
            user.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
            user.setLastName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LAST_NAME)));
            user.setFirstName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FIRST_NAME)));
            user.setMailAddress(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MAIL_ADDRESS)));
            user.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE)));
            user.setPost(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POST)));
            user.setCompanyAffiliation(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COMPANY_AFFILIATION)));
            user.setLanguage(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LANGUAGE)));
            user.setLogin(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LOGIN)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD)));
            user.setServer(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SERVER)));
            user.setPort(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PORT)));
            user.setNameDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_DB)));
            user.setUserDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_DB)));
            user.setPasswordDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD_DB)));
        }
        cursor.close();
        return  users;
    }
    
    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE_USERS);
    }
    
    @SuppressLint("Range")
    public User getUserById(long id){
        User user = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",DatabaseHelper.TABLE_USERS, DatabaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            user.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
            user.setLastName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LAST_NAME)));
            user.setFirstName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FIRST_NAME)));
            user.setMailAddress(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MAIL_ADDRESS)));
            user.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE)));
            user.setPost(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POST)));
            user.setCompanyAffiliation(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COMPANY_AFFILIATION)));
            user.setLanguage(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LANGUAGE)));
            user.setLogin(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LOGIN)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD)));
            user.setServer(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SERVER)));
            user.setPort(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PORT)));
            user.setNameDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_DB)));
            user.setUserDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_DB)));
            user.setPasswordDB(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD_DB)));
        }
        cursor.close();
        return  user;
    }
    
    public long insert(User user){
        
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_LAST_NAME, user.getLastName());
        cv.put(DatabaseHelper.COLUMN_FIRST_NAME, user.getFirstName());
        cv.put(DatabaseHelper.COLUMN_MAIL_ADDRESS, user.getMailAddress());
        cv.put(DatabaseHelper.COLUMN_PHONE, user.getPhone());
        cv.put(DatabaseHelper.COLUMN_POST, user.getPost());
        cv.put(DatabaseHelper.COLUMN_COMPANY_AFFILIATION, user.getCompanyAffiliation());
        cv.put(DatabaseHelper.COLUMN_LANGUAGE, user.getLanguage());
        cv.put(DatabaseHelper.COLUMN_LOGIN, user.getLogin());
        cv.put(DatabaseHelper.COLUMN_PASSWORD, user.getPassword());
        cv.put(DatabaseHelper.COLUMN_SERVER, user.getServer());
        cv.put(DatabaseHelper.COLUMN_PORT, user.getPort());
        cv.put(DatabaseHelper.COLUMN_NAME_DB, user.getNameDB());
        cv.put(DatabaseHelper.COLUMN_USER_DB, user.getUserDB());
        cv.put(DatabaseHelper.COLUMN_PASSWORD_DB, user.getPasswordDB());
        
        return  database.insert(DatabaseHelper.TABLE_USERS, null, cv);
    }
    
    public long deleteUserById(long userId){
        
        String whereClause = "id = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};
        return database.delete(DatabaseHelper.TABLE_USERS, whereClause, whereArgs);
    }
    
    public long update(User user){
        
        String whereClause = DatabaseHelper.COLUMN_ID + "=" + user.getId();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_LAST_NAME, user.getLastName());
        cv.put(DatabaseHelper.COLUMN_FIRST_NAME, user.getFirstName());
        cv.put(DatabaseHelper.COLUMN_MAIL_ADDRESS, user.getMailAddress());
        cv.put(DatabaseHelper.COLUMN_PHONE, user.getPhone());
        cv.put(DatabaseHelper.COLUMN_POST, user.getPost());
        cv.put(DatabaseHelper.COLUMN_COMPANY_AFFILIATION, user.getCompanyAffiliation());
        cv.put(DatabaseHelper.COLUMN_LANGUAGE, user.getLanguage());
        cv.put(DatabaseHelper.COLUMN_LOGIN, user.getLogin());
        cv.put(DatabaseHelper.COLUMN_PASSWORD, user.getPassword());
        cv.put(DatabaseHelper.COLUMN_SERVER, user.getServer());
        cv.put(DatabaseHelper.COLUMN_PORT, user.getPort());
        cv.put(DatabaseHelper.COLUMN_NAME_DB, user.getNameDB());
        cv.put(DatabaseHelper.COLUMN_USER_DB, user.getUserDB());
        cv.put(DatabaseHelper.COLUMN_PASSWORD_DB, user.getPasswordDB());
        return database.update(DatabaseHelper.TABLE_USERS, cv, whereClause, null);
    }
    @SuppressLint("Range")
    public User getUserByLoginAndPassword(String login, String password){
        List<User> usersList = getUsers();
        Reception reception;
        Engineer engineer;
        
        for(User u : usersList){
            if (u.getLogin().equals(login) && u.getPassword().equals(password)){
                if(u.getPost().equals("Engineer") || u.getPost().equals("Инженер")) {
                    engineer = (Engineer) u;
                    return engineer;
                }
                if(u.getPost().equals("Reception Secretary") || u.getPost().equals("Секретарь приемной")){
                    reception = (Reception) u;
                    return reception;
                }
            }
        }
        
        return null;
    }
}
