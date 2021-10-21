package com.example.uspherejda.DB;

import android.provider.BaseColumns;

public class SatFormContracts {
    private SatFormContracts(){}
    public static class ContactsEntry implements BaseColumns {
        public static final String TABLE_NAME = "form";
        public static final String ID = "id";
        public static final String COLUMN_NAME_TITLE = "name";
        public static final String COLUMN_COUNTRY_TITLE = "country";
        public static final String COLUMN_CATEGORY_TITLE = "category";


    }
}
