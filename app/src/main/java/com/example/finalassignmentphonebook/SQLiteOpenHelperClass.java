package com.example.finalassignmentphonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class SQLiteOpenHelperClass extends SQLiteOpenHelper {



    private static final String NAME_of_DATABASE = "phonebookdatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_F_NAME = "firstname";
    private static final String COLUMN_L_NAME = "lastname";
    private static final String COLUMN_ADDRESS = "address";

//    private static final String COLUMN_DATE = "dateadded";
    private static final String COLUMN_PHONENUMBER = "phonenumber";


    public SQLiteOpenHelperClass(@Nullable Context context) {
        // cursor factory is when you are using your own custom cursor
        super(context, NAME_of_DATABASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER NOT NULL CONSTRAINT employee_pk PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_F_NAME + " varchar(200) NOT NULL, " +
                COLUMN_L_NAME + " varchar(200) NOT NULL, " +
                COLUMN_ADDRESS + " varchar(200) NOT NULL, " +
                COLUMN_PHONENUMBER + " varchar(200) NOT NULL);";

                db.execSQL(sql);

//        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);

    }
    boolean addContact(String fname, String lname, String  address , String phonenumber) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_F_NAME, fname);
        cv.put(COLUMN_L_NAME, lname);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_PHONENUMBER, phonenumber);


        return sqLiteDatabase.insert(TABLE_NAME, null, cv) != -1;

    }



    Cursor getAllContacts() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    boolean updateContacts(int id ,String fname, String lname,  String  address , String phonenumber) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // using the Calendar object to get the current time
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String currentdate = sdf.format(calendar.getTime());

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_F_NAME, fname);
        cv.put(COLUMN_L_NAME, lname);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_PHONENUMBER, phonenumber);




        // this method returns the number of rows affected
        return sqLiteDatabase.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    boolean deleteContact(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // the delete method returns the number of rows affected
        return sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    public int getProfilesCount() {
        Cursor cursor = getAllContacts();
        int count = 0;
        if (cursor.moveToFirst()) {
            count += cursor.getCount();
            } while (cursor.moveToNext());
            cursor.close();
            return count;


    }



}







