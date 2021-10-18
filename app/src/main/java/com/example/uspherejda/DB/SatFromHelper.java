package com.example.uspherejda.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.uspherejda.Model.SatelliteForm;

public class SatFromHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contacts.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + SatFormContracts.ContactsEntry.TABLE_NAME +
            " (" + SatFormContracts.ContactsEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            SatFormContracts.ContactsEntry.COLUMN_NAME_TITLE + " VARCHAR(20)," +
            SatFormContracts.ContactsEntry.COLUMN_COUNTRY_TITLE + " VARCHAR(20)," +
            SatFormContracts.ContactsEntry.COLUMN_CATEGROY_TITLE + " VARCHAR(20) )";

    public SatFromHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertContact(SQLiteDatabase db, SatelliteForm sf){
        //Check the bd is open
        if (db.isOpen()){
            //Creation of the register for insert object with the content values
            ContentValues values = new ContentValues();
            //Insert the incidence getting all values
            values.put(SatFormContracts.ContactsEntry.COLUMN_NAME_TITLE, sf.getName());

            db.insert(SatFormContracts.ContactsEntry.TABLE_NAME, null, values);
        }else{
            Log.i("sql","Database is closed");
        }
    }
}
