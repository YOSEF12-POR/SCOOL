package com.example.nano_school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Log_in extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Access_DataBase access_dataBase;
    Student student;
    EditText et_id, et_password;
    Button btn_log_in;

    @Override
    public void onBackPressed() {
        Toast.makeText(getBaseContext(), "الله معك", Toast.LENGTH_SHORT).show();

        finishAffinity();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        student = null;
        access_dataBase = Access_DataBase.getInstance(getBaseContext());
        sp = getSharedPreferences("std_id", MODE_PRIVATE);


        et_id = findViewById(R.id.log_in_et_username);
        et_password = findViewById(R.id.log_in_et_password);
        btn_log_in = findViewById(R.id.log_in_btn_logIn);
        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_id.getText().toString().isEmpty() || et_password.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), " دخل الاي دي و كلمةا لسر    ", Toast.LENGTH_SHORT).show();
                } else {
                    log_in(Integer.parseInt(et_id.getText().toString()), et_password.getText().toString());
                }


            }
        });


    }

    public boolean log_in(int id, String passwrod) {
        access_dataBase.open();
        student = access_dataBase.search_std(id);
//        if (student!=null)
//        Toast.makeText(getBaseContext(), student.getName_std(), Toast.LENGTH_LONG).show();
        access_dataBase.close();

        // Thread.sleep(5000);
        editor = sp.edit();
        if (student != null) {
            if (student.getPassword().equals(passwrod)) {
                editor.putInt("id", student.getId_std());
                editor.apply();

                Intent i = new Intent(getBaseContext(), MainActivity.class);
                i.putExtra("std_obj", student);
                startActivity(i);
            } else {

                Toast.makeText(getBaseContext(), "كلمة السر مش مزبوطة", Toast.LENGTH_LONG).show();


            }

        } else {
            Toast.makeText(getBaseContext(), " الاي دي   غلط", Toast.LENGTH_LONG).show();


        }

        return true;
    }
//    public boolean log_in(int id, String passwrod) {
//        access_dataBase.open();
//
//        student = access_dataBase.search_std(id);
////        Toast.makeText(getBaseContext(), student.getName_std(), Toast.LENGTH_LONG).show();
//        access_dataBase.close();
//        if (student != null) {
//            sp = getSharedPreferences("std_id", MODE_PRIVATE);// مود برايفت عشان ما حد يصل للبيانات من خارج التطبيق , اما الاس تي دي اي دي هاد رمز بس
//            editor = sp.edit();
//            editor.putInt("id", student.getId_std());
//            editor.apply();
//
////            if (sp.getInt("id", -1) != -1) {
////                editor.clear();
////
////
////            }
//            if (student.getPassword().equals(et_password.getText().toString())) {
//
//                editor.putInt("id", student.getId_std());
//                Intent i = new Intent(getBaseContext(), MainActivity.class);
//                i.putExtra("std_obj", student);
//                startActivity(i);
//            } else {
//
//                Toast.makeText(getBaseContext(), "كلمة السر  غلط ", Toast.LENGTH_LONG).show();
//                editor.putInt("id", -1);
//                editor.apply();
//                return false;
//            }
//
//            return true;
//
//        } else {
//
//            Toast.makeText(getBaseContext(), "  الاي دي مش مزبوط ", Toast.LENGTH_LONG).show();
//
//            editor.putInt("id", -1);
//            editor.apply();
//            return false;
//
//        }
//    }
}