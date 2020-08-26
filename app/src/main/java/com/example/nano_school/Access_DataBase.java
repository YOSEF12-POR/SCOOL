package com.example.nano_school;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

class Access_DataBase {
    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static Access_DataBase Instance;

    private Access_DataBase(Context context) {
        this.sqLiteOpenHelper = new My_DataBase2(context);


    }

    public static Access_DataBase getInstance(Context context) {
        if (Instance == null)
            Instance = new Access_DataBase(context);

        return Instance;
    }


    public void open() {
        this.sqLiteDatabase = this.sqLiteOpenHelper.getWritableDatabase();


    }

    public void close() {
        if (this.sqLiteDatabase != null)
            this.sqLiteDatabase.close();


    }

    public long itmes_count_STD_TB() {
        return DatabaseUtils.queryNumEntries(sqLiteDatabase, My_DataBase2.TB_STD);


    }

    public long itmes_count_SUB_TB() {
        return DatabaseUtils.queryNumEntries(sqLiteDatabase, My_DataBase2.TB_SUB);


    }
    // 5 : 52


    public ArrayList<Subject> get_sub() {
        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT sub.id_sub,sub.name_sub , doc.name_doc from sub INNER JOIN doc on  \n" +
                "sub.id_doc=doc.id_doc ", null);
        ArrayList<Subject> arrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
//int id_sub, String name_sub, int number_std
            do {
                int id = cursor.getInt(cursor.getColumnIndex(My_DataBase2.SUB_CLN1_ID));
                String name_sub = cursor.getString(cursor.getColumnIndex(My_DataBase2.SUB_CLN2_NAME));
                //    int num_sub = cursor.getInt(cursor.getColumnIndex(My_DataBase2.SUB_CLN3_NUM_STD));ٍ
                String name_doc = cursor.getString(cursor.getColumnIndex(My_DataBase2.DOC_CLN2_NAME));
                arrayList.add(new Subject(id, name_sub, 0, name_doc));

            } while (cursor.moveToNext());
            cursor.close();


        }

        return arrayList;
    }

    public Student get_std(Student student) {
        String ar[] = {student.getId_std() + ""};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  std   where  std.id_std  =?", ar);
        Student arrayList = null;
        if (cursor.moveToFirst()) {
//int id_std, String name_std, int age, String password
            //     do {
            int id = cursor.getInt(cursor.getColumnIndex(My_DataBase2.STD_CLN3_ID));
            String name_std = cursor.getString(cursor.getColumnIndex(My_DataBase2.STD_CLN1_NAME));
            int age = cursor.getInt(cursor.getColumnIndex(My_DataBase2.STD_CLN6_AGE));
            String passwrod = cursor.getString(cursor.getColumnIndex(My_DataBase2.STD_CLN4_PASSWORD));
            int level = cursor.getInt(cursor.getColumnIndex(My_DataBase2.STD_CLN5_LEVEL));
            double rate = cursor.getDouble(cursor.getColumnIndex(My_DataBase2.STD_CLN2_REATE));

            arrayList = new Student(id, name_std, age, passwrod, rate, level);

            // } while (cursor.moveToNext());
            cursor.close();


        }

        return arrayList;
    }

    public boolean reg_sub(Subject subject, Student student) {
        Date date = new Date();
        ArrayList<Subject> subjectArrayList = search_subs(student);
        for (int i = 0; i < subjectArrayList.size(); i++) {
            if (subjectArrayList.get(i).getId_sub() == subject.getId_sub()) {

                return false;
            }

        }
        ContentValues contentValues = new ContentValues();// بنستخدمو عشان نطلع القيم

        contentValues.put(My_DataBase2.REG_CLN1_ID_STD, student.getId_std());
        contentValues.put(My_DataBase2.REG_CLN2_ID_SUB, subject.getId_sub());
        contentValues.put(My_DataBase2.REG_DATE, date.toString());

        long s = sqLiteDatabase.insert(My_DataBase2.TB_REG_SUB, null, contentValues);

        if (s == -1) {
            return false;

        }


        // update_sub(id_sub);
        return true;

    }

    public boolean update_std_details(Student student) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(My_DataBase2.STD_CLN4_PASSWORD, student.getPassword());
        //   contentValues.put(My_DataBase2.CLN_NAME, items.getName_book());
        //   contentValues.put(My_DataBase2.CLN4_AMOUNT, items.getPrice_book());


        String agrs[] = {student.getId_std() + ""};

        int x = sqLiteDatabase.update(My_DataBase2.TB_STD, contentValues, My_DataBase2.STD_CLN3_ID + " =?", agrs);
        if (x == 0)
            return false;
        return true;


    }

    public boolean update_sub(int id) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(My_DataBase2.SUB_CLN3_NUM_STD, 10);
        //  contentValues.put(My_DataBase2.CLN_NAME, items.getName_book());
        //  contentValues.put(My_DataBase2.CLN4_AMOUNT, items.getPrice_book());


        String agrs[] = {id + ""};

        int x = sqLiteDatabase.update(My_DataBase2.TB_SUB, contentValues, My_DataBase2.TB_SUB + "." + My_DataBase2.SUB_CLN1_ID + "  =?", agrs);
        if (x == 0)
            return false;
        return true;


    }

    public ArrayList<Subject> search_subs(Student student) {
        String a[] = {student.getId_std() + ""};
        String sql = "SELECT sub.id_sub,sub.name_sub,doc.name_doc FROM sub INNER JOIN doc on sub.id_doc = doc.id_doc WHERE  sub.id_sub in\n" +
                "(SELECT reg_sub.id_sub FROM reg_sub WHERE reg_sub.id_std = ? )  ";//   ب الشكل هاد دايما


        Cursor cursor = sqLiteDatabase.rawQuery(sql, a);

//
//        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + My_DataBase2.TB_SUB + " left join  "
//                + My_DataBase2.TB_SUB_STD + " on " + My_DataBase2.TB_SUB + "." + My_DataBase2.SUB_CLN1_ID + " = " +
//                My_DataBase2.TB_STD + "." + My_DataBase2.STD_CLN3_ID
//                + " where " + My_DataBase2.TB_SUB + "." + My_DataBase2.SUB_CLN1_ID + "  = ? ", a);
        ArrayList<Subject> subjects = new ArrayList<>();
        if (cursor.moveToFirst()) {

            //int id_sub, String name_sub, int number_std
            do {


                int id_sub = cursor.getInt(cursor.getColumnIndex(My_DataBase2.SUB_CLN1_ID));
                String name_sub = cursor.getString(cursor.getColumnIndex(My_DataBase2.SUB_CLN2_NAME));
//                int num_sub1 = cursor.getInt(cursor.getColumnIndex(My_DataBase2.SUB_CLN3_NUM_STD));ٍ
                String name_doc = cursor.getString(cursor.getColumnIndex(My_DataBase2.DOC_CLN2_NAME));
                subjects.add(new Subject(id_sub, name_sub, 0, name_doc));

            } while (cursor.moveToNext());
            cursor.close();
        }
        return subjects;
    }

    public Student search_std(int id) {
        String a[] = {id + ""};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  std   where  std.id_std   = ? ", a);
        Student student = null;


        //int id_std, String name_std, int age, String password
        if (cursor.moveToFirst()) {

            int id_std = cursor.getInt(cursor.getColumnIndex(My_DataBase2.STD_CLN3_ID));
            int level = cursor.getInt(cursor.getColumnIndex(My_DataBase2.STD_CLN5_LEVEL));
            double rate = cursor.getDouble(cursor.getColumnIndex(My_DataBase2.STD_CLN2_REATE));
            String name_std = cursor.getString(cursor.getColumnIndex(My_DataBase2.STD_CLN1_NAME));
            int age = cursor.getInt(cursor.getColumnIndex(My_DataBase2.STD_CLN6_AGE));
            String password = cursor.getString(cursor.getColumnIndex(My_DataBase2.STD_CLN4_PASSWORD));

            student = new Student(id_std, name_std, age, password, rate, level);
        }
        cursor.close();

        return student;
    }

    public boolean remove_itmes(Subject subject, Student student) {

        String agrs[] = {student.getId_std() + "", subject.getId_sub() + ""};

        int s = sqLiteDatabase.delete(My_DataBase2.TB_REG_SUB, My_DataBase2.TB_REG_SUB
                + "." + My_DataBase2.REG_CLN1_ID_STD + "=? and " + My_DataBase2.TB_REG_SUB + "." + My_DataBase2.REG_CLN2_ID_SUB + " = ? ", agrs);
        if (s == 0)
            return false;
        return true;


    }

//    public Student search_std_sub(int id_std, int id_sub) {
//        String a[] = {id_std + "", id_sub + ""};
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  std_sub   where  std_sub.id_std = " + id_std + " & " + id_sub + " = std_sub.id+sub  ", null);
//        Student student = null;
//
//
//        //int id_std, String name_std, int age, String password
//        if (cursor.moveToFirst()) {
//
//            int id_std1 = cursor.getInt(cursor.getColumnIndex(My_DataBase2.STD_CLN3_ID));
//
//            String name_std = cursor.getString(cursor.getColumnIndex(My_DataBase2.STD_CLN1_NAME));
//            int age = cursor.getInt(cursor.getColumnIndex(My_DataBase2.STD_CLN2_AGE));
//            String password = cursor.getString(cursor.getColumnIndex(My_DataBase2.STD_CLN4_PASSWORD));
//
//            student = new Student(id_std1, name_std, age, password);
//        }
//        cursor.close();
//
//        return student;
//    }
}
