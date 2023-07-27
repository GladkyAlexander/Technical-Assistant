package ru.great_larder.technical_assistant_android.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_USERS = "user";
    private static final String DATABASE_NAME = "technical_assistant.db";
    private static final int VERSION = 2;
    
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LAST_NAME = "lastName" ;
    public static final String COLUMN_FIRST_NAME = "firstName" ;
    public static final String COLUMN_MAIL_ADDRESS = "mailAddress";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_POST = "post";
    public static final String COLUMN_COMPANY_AFFILIATION = "companyAffiliation" ;
    public static final String COLUMN_LANGUAGE = "language";
    public static final String COLUMN_LOGIN = "login" ;
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_SERVER = "server";
    public static final String COLUMN_PORT = "port";
    public static final String COLUMN_NAME_BD = "nameDB";
    public static final String COLUMN_USER_DB = "userDB" ;
    public static final String COLUMN_PASSWORD_DB = "passwordDB";
    public static final String COLUMN_SERVER_FTP = "serverFTP" ;
    public static final String COLUMN_PORT_FTP = "portFTP";
    public static final String COLUMN_USER_FTP = "userFTP" ;
    public static final String COLUMN_PASSWORD_FTP = "passwordFTP";
    public static final String NEW_COLUMN = "";
    
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLiteUser.CREATE_TABLE_USER);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        
        if(!existsColumnInTable(sqLiteDatabase, TABLE_USERS, NEW_COLUMN)){
            sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_USERS + " ADD COLUMN " + NEW_COLUMN + " TEXT DEFAULT null");
        }
    }
    private boolean existsColumnInTable(SQLiteDatabase inDatabase, String inTable, String columnToCheck) {
        Cursor cursor = null;
        try {
            // Query 1 row
            cursor = inDatabase.rawQuery("SELECT * FROM " + inTable + " LIMIT 0", null);
            
            // getColumnIndex() gives us the index (0 to ...) of the column - otherwise we get a -1
            if (cursor.getColumnIndex(columnToCheck) != -1)
                return true;
            else
                return false;
            
        } catch (Exception Exp) {
            return false;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
}
