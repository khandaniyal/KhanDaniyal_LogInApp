package com.dani.uspherejda.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.dani.uspherejda.Model.SatelliteForm;
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
    //create fb
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //delete all elements into the table
    public void onDelete(SQLiteDatabase db){
        db.execSQL("delete from " + SatFormContracts.ContactsEntry.TABLE_NAME);
    }
    //insert the elements into the rows
    public void insertContact(SQLiteDatabase db, SatelliteForm sf){
        //Check the bd is open
        if (db.isOpen()){
            //Creation of the register for insert object with the content values
            ContentValues values = new ContentValues();
            //Insert the incidence getting all values
            Log.i("sqlitelog", sf.getName() + " " + sf.getCategory() + " " + sf.getCountry());
            //put the values into the db
            values.put(SatFormContracts.ContactsEntry.COLUMN_NAME_TITLE, sf.getName());
            values.put(SatFormContracts.ContactsEntry.COLUMN_COUNTRY_TITLE, sf.getCountry());
            values.put(SatFormContracts.ContactsEntry.COLUMN_CATEGORY_TITLE, sf.getCategory());
            //inserts the values above
            db.insert(SatFormContracts.ContactsEntry.TABLE_NAME, null, values);
        }else{
            Log.i("sql","Database is closed");
        }
    }
    //this method removes a specific item from the  db
    public void removeSatelite(SQLiteDatabase sqLiteDatabase, int pos){
        sqLiteDatabase.execSQL("delete from form where id=" + pos);
    }
    //reads from and stores the selected items from the db
    public ArrayList<SatelliteForm> getAllData(SQLiteDatabase sqLiteDatabase) {
        ArrayList<SatelliteForm> arraySatelite = new ArrayList<>();
        sqLiteDatabase = this.getWritableDatabase();
        String SELECT_QUERY = "select id, name, country, category from " + SatFormContracts.ContactsEntry.TABLE_NAME + ";";
        //this cursor will navigate through the db
        Cursor c = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
        Log.i("nameSQL", "" + c.getCount());
        if(c.moveToFirst()) {
            do{
                SatelliteForm form = new SatelliteForm();
                form.setId(c.getInt(0));
                form.setName(c.getString(1));
                form.setCountry(c.getString(2));
                form.setCategory(c.getString(3));
                Log.i("nameSQL", c.getInt(0) + "" + c.getString(1) + "" + c.getString(2) + "" + c.getString(3));
                arraySatelite.add(form);
            }while (c.moveToNext());
        }
        c.close();
        return arraySatelite;
    }
}
