package com.example.nano_school;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

class My_DataBase2 extends SQLiteAssetHelper {
    public final static String DB_NAME = "SChoole_DB_3.db";
    public final static String TB_STD = "std";
    public final static String TB_SUB = "sub";
    public final static String TB_REG_SUB = "reg_sub";
     public final static String TB_DOC = "doc";

    public final static String DOC_CLN1_ID = "id_doc";
    public final static String DOC_CLN2_NAME = "name_doc";



    public final static String REG_CLN1_ID_STD = "id_std";
    public final static String REG_CLN2_ID_SUB = "id_sub";
    public final static String REG_DATE = "date";


    public final static String STD_CLN1_NAME = "name_std";
    public final static String STD_CLN2_REATE = "rate_std";
    public final static String STD_CLN3_ID = "id_std";
    public final static String STD_CLN4_PASSWORD = "password";
    public final static String STD_CLN5_LEVEL = "level_std";
    public final static String STD_CLN6_AGE = "age_std";

    public final static String SUB_CLN1_ID = "id_sub";
    public final static String SUB_CLN2_NAME = "name_sub";
    public final static String SUB_CLN3_NUM_STD = "num_std";
    public final static String SUB_CLN4_ID = "id_doc";

    public final static int DB_VERSION = 1;

    public My_DataBase2(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }}
