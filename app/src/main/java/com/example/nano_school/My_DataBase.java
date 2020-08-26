package com.example.nano_school;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

class My_DataBase extends SQLiteAssetHelper {
    public final static String DB_NAME = "smalSchool.db";
    public final static String TB_STD = "std";
    public final static String TB_SUB = "sub";
    public final static String TB_SUB_STD = "std_sub";
    public final static String STD_CLN1_NAME = "name_std";
    public final static String STD_CLN2_AGE = "age_std";
    public final static String STD_CLN3_ID = "id_std";
    public final static String STD_CLN4_PASSWORD = "password_std";
    public final static String SUB_CLN1_ID = "id_sub";
    public final static String SUB_CLN2_NAME = "name_sub";
    public final static String SUB_CLN3_NUM_STD = "num_std";
    public final static String SUB_STD_ID_STD = "id_std";
    public final static String SUB_STD_ID_SUB = "id_sub";
    public final static String SUB_STD_DATE = "date";
    public final static int DB_VERSION = 2;

        public My_DataBase(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        super.onUpgrade(db, oldVersion, newVersion);
//            db.execSQL("drop table if exists "+TABLE_CONTACTS);
//        onCreate(db);
//    }
}
