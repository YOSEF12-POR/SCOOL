package com.example.nano_school;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class Show_Sub extends AppCompatActivity {
    RecyclerView recyclerView;
    Adaptar_sub adaptar_sub;
    Access_DataBase access_dataBase;
    ArrayList<Subject> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__sub);
        recyclerView = findViewById(R.id.show_rc);
        access_dataBase=Access_DataBase.getInstance(this);
        Intent i = getIntent();
        Student student = (Student) i.getSerializableExtra("std_obj");
        access_dataBase.open();
        arrayList = new ArrayList<>();
        arrayList = access_dataBase.get_sub();
        access_dataBase.close();

        adaptar_sub = new Adaptar_sub(arrayList, getBaseContext(), new OnClckSub() {
            @Override
            public void OnCLick(Subject subject) {

            }
        });
        RecyclerView.LayoutManager l = new GridLayoutManager(this, 1);


        recyclerView.setLayoutManager(l);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptar_sub);

    }
}