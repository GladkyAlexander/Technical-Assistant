package ru.great_larder.technicalAssistant.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import ru.great_larder.technicalAssistant.domain.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    
    public static final String TABLE_USERS = "users";
    private static final String DATABASE_NAME = "technical_assistant.db";
    private static final int SCHEMA = 1;
    
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LAST_NAME = "lastName" ;
    public static final String COLUMN_FIRST_NAME = "firstName";
    public static final String COLUMN_MAIL_ADDRESS = "mailAddress";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_POST = "post";
    public static final String COLUMN_COMPANY_AFFILIATION = "companyAffiliation";
    public static final String COLUMN_LANGUAGE = "language";
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_SERVER = "server";
    public static final String COLUMN_PORT = "port";
    public static final String COLUMN_NAME_DB = "nameDB";
    public static final String COLUMN_USER_DB = "userDB";
    public static final String COLUMN_PASSWORD_DB = "passwordDB";
    
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLiteUser.CREATE_TABLE_USER);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
}
