package com.example.uspherejda.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.uspherejda.Model.SatelliteForm;

import java.util.ArrayList;

public class SatFromHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contacts.db";

    //private static final String SQL_DROP_ENTRIES = "DELETE * TABLE " + SatFormContracts.ContactsEntry.TABLE_NAME + ";";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + SatFormContracts.ContactsEntry.TABLE_NAME +
            " (" + SatFormContracts.ContactsEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            SatFormContracts.ContactsEntry.COLUMN_NAME_TITLE + " VARCHAR(10)," +
            SatFormContracts.ContactsEntry.COLUMN_COUNTRY_TITLE + " VARCHAR(10)," +
            SatFormContracts.ContactsEntry.COLUMN_CATEGORY_TITLE + " VARCHAR(10));";


    public SatFromHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void onDelete(SQLiteDatabase db){
        db.execSQL("delete from " + SatFormContracts.ContactsEntry.TABLE_NAME);
    }
    public void insertContact(SQLiteDatabase db, SatelliteForm sf){
        //Check the bd is open
        if (db.isOpen()){
            //Creation of the register for insert object with the content values
            ContentValues values = new ContentValues();
            //Insert the incidence getting all values
            Log.i("sqlitelog", sf.getName() + " " + sf.getCategory() + " " + sf.getCountry());
            values.put(SatFormContracts.ContactsEntry.COLUMN_NAME_TITLE, sf.getName());
            values.put(SatFormContracts.ContactsEntry.COLUMN_COUNTRY_TITLE, sf.getCountry());
            values.put(SatFormContracts.ContactsEntry.COLUMN_CATEGORY_TITLE, sf.getCategory());
            //inserts the values above
            db.insert(SatFormContracts.ContactsEntry.TABLE_NAME, null, values);
        }else{
            Log.i("sql","Database is closed");
        }
    }


    public ArrayList<SatelliteForm> getAllData(SQLiteDatabase sqLiteDatabase){
        ArrayList<SatelliteForm> arraySatelite = new ArrayList<>();
        sqLiteDatabase = this.getWritableDatabase();
        String SELECT_QUERY = "select name, country, category from " + SatFormContracts.ContactsEntry.TABLE_NAME + ";";
        Cursor c = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
        if(c.moveToFirst()){
            while(c.moveToNext()){
                SatelliteForm form = new SatelliteForm();
                form.setName(c.getString(0));
                form.setCountry(c.getString(1));
                form.setCategory(c.getString(2));
                Log.i("nameSQL", c.getString(0) + "" + c.getString(1) + "" + c.getString(2));
                arraySatelite.add(form);
            }
        }
        c.close();
        return arraySatelite;
    }
}
