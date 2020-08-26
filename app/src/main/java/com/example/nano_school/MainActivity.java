package com.example.nano_school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button btn_reg_sub, btn_wal_sub, btn_show_sub, btn_log_out,btn_show_details_std;
    SharedPreferences sp;
    Access_DataBase access_dataBase;
    Student student;
    SharedPreferences.Editor editor;
    int i = 1;


    @Override
    public void onBackPressed() {
        int x = 0;
        i++;
        if (i == 5) {
            i = 1;

            finishAffinity();
            x = 1;

        }
        if (x != 1)
            onBackPressed();
        else {
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("std_id", MODE_PRIVATE);
        access_dataBase = Access_DataBase.getInstance(this);

        if (sp.getInt("id", -1) != -1) {
            Intent intent = getIntent();

            student = (Student) intent.getSerializableExtra("std_obj");
            if (student == null) {
                access_dataBase.open();
                student = access_dataBase.search_std(sp.getInt("id", -1));
                access_dataBase.close();
            }


        } else {
            Intent i = new Intent(getBaseContext(), Log_in.class);
            Intent intent = getIntent();

            student = (Student) intent.getSerializableExtra("std_obj");
            startActivity(i);

        }

        btn_show_details_std=findViewById(R.id.main_btn_details_std);
        btn_reg_sub = findViewById(R.id.main_btn_reg_sub);
        btn_wal_sub = findViewById(R.id.main_btn_wal_sub);
        btn_show_sub = findViewById(R.id.main_btn_show_sub);
        btn_log_out = findViewById(R.id.main_btn_log_out);

        btn_reg_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Add_sub.class);
                i.putExtra("std_obj", student);
                startActivity(i);
            }
        });

        btn_wal_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Withdrawal_Sub.class);
                i.putExtra("std_obj", student);
                startActivity(i);
            }
        });

        btn_show_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Show_Sub.class);
                i.putExtra("std_obj", student);
                startActivity(i);
            }
        });


        btn_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student = null;
                editor = sp.edit();
                editor.clear();
                editor.putInt("id", -1);
                editor.apply();
                Intent i = new Intent(getBaseContext(), Log_in.class);

                startActivity(i);
            }
        });

    btn_show_details_std.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getBaseContext(), Student_Details.class);
            i.putExtra("std_obj", student);

            startActivity(i);
        }
    });


    }
}