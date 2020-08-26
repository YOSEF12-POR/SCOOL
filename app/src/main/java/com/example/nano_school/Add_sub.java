package com.example.nano_school;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.ArrayList;

public class Add_sub extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter_Add_Sub adaptar_sub;
    Access_DataBase access_dataBase;
    ArrayList<Subject> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub);
        recyclerView = findViewById(R.id.add_rc);
        try {
            Intent i = getIntent();
            access_dataBase = Access_DataBase.getInstance(getBaseContext());

            final Student student = (Student) i.getSerializableExtra("std_obj");
            access_dataBase.open();
            arrayList = new ArrayList<>();
            arrayList = access_dataBase.get_sub();
            access_dataBase.close();


            adaptar_sub = new Adapter_Add_Sub(arrayList, getBaseContext(), new OnClckSub() {
                @Override
                public void OnCLick(Subject subject) {
                    if (subject != null && student != null) {
                        access_dataBase.open();
                        //  boolean t = false;
                        //  if (access_dataBase.search_std_sub(student.getId_std(), subject.getId_sub()) != null) {

                        boolean t = access_dataBase.reg_sub(subject, student);
                        //   }
                        access_dataBase.close();

                        if (t) {
                            Toast.makeText(getBaseContext(), "تم تسجيل المادة", Toast.LENGTH_SHORT).show();

                        }
                        if (!t) {
                            Toast.makeText(getBaseContext(), "المادة امسجله من اول", Toast.LENGTH_SHORT).show();


                        }
                        t = false;
                    }
                }
            });
        } catch (Exception e) {
        }

        RecyclerView.LayoutManager l = new GridLayoutManager(this, 1);


        recyclerView.setLayoutManager(l);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptar_sub);

    }
}