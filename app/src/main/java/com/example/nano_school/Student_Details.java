package com.example.nano_school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Student_Details extends AppCompatActivity {
    TextView tv_name, tv_id_std, tv_age, tv_level, tv_rate;
    Button btn_new_pass, btn_save_pass;
    EditText et_new_pass;
    Access_DataBase access_dataBase;
    Student student;
    TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__details);
        tv_name = findViewById(R.id.details_std_tv_name);
        tv_id_std = findViewById(R.id.details_std_tv_id_number);
        tv_age = findViewById(R.id.details_std_tv_age);
        tv_level = findViewById(R.id.details_std_tv_level);
        tv_rate = findViewById(R.id.details_std_tv_rate);
        et_new_pass = findViewById(R.id.details_std_et_new_password);
        btn_new_pass = findViewById(R.id.details_std_btn_change_pass);
        btn_save_pass = findViewById(R.id.details_std_btn_save_new_pass);
        textInputLayout = findViewById(R.id.details_std_layout_new_password);

        Intent i = getIntent();
        student = (Student) i.getSerializableExtra("std_obj");
        access_dataBase = Access_DataBase.getInstance(this);
        access_dataBase.open();
        access_dataBase.get_std(student);
        access_dataBase.close();
        tv_name.setText("اسم الطالب : " + student.getName_std());
        tv_id_std.setText("الرقم الجامعي : " + student.getId_std());
        tv_age.setText("العمر  : " + student.getAge());
        tv_level.setText("المستوى : " + student.getLevel());
        tv_rate.setText("المعدل التراكمي : " + student.getRate());

        btn_new_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_save_pass.setVisibility(View.VISIBLE);
                textInputLayout.setVisibility(View.VISIBLE);

            }
        });
        btn_save_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(et_new_pass.getText().toString().isEmpty())) {
                    access_dataBase.open();
                    student.setPassword(et_new_pass.getText().toString());
                    boolean t = access_dataBase.update_std_details(student);
                    access_dataBase.close();
                    if (t)
                        Toast.makeText(getBaseContext(), "تم تغيير كلمة المرور ", Toast.LENGTH_LONG).show();
                    textInputLayout.setVisibility(View.GONE);
                    btn_save_pass.setVisibility(View.GONE);
                }else {
                    Toast.makeText(getBaseContext(),"ادخل كلمة سر جديدة",Toast.LENGTH_LONG).show();

                }
            }


        });


    }
}