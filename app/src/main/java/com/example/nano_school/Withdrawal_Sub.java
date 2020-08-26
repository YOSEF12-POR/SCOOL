package com.example.nano_school;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Withdrawal_Sub extends AppCompatActivity {
    RecyclerView recyclerView;
    Adaptar_sub adaptar_sub;
    Access_DataBase access_dataBase;
    ArrayList<Subject> arrayList;
    SharedPreferences sp;
    public static boolean s = false;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal__sub);
        recyclerView = findViewById(R.id.wal_rc);
        sp = getSharedPreferences("std_id", MODE_PRIVATE);
        id = sp.getInt("id", -1);
        if (id != -1) {
            Intent i = getIntent();
            access_dataBase = Access_DataBase.getInstance(getBaseContext());

            final Student student = (Student) i.getSerializableExtra("std_obj");
            access_dataBase.open();
            arrayList = new ArrayList<>();
            arrayList = access_dataBase.search_subs(student);
            access_dataBase.close();

            adaptar_sub = new Adaptar_sub(arrayList, getBaseContext(), new OnClckSub() {
                @Override
                public void OnCLick(Subject subject) {

                    access_dataBase.open();

                    s = access_dataBase.remove_itmes(subject, student);
//                 if (subject!=null)
//                    arrayList.remove(subject);

                    adaptar_sub.notifyDataSetChanged();
                    arrayList.remove(subject);
                    arrayList = access_dataBase.search_subs(student);
                    access_dataBase.close();
                    adaptar_sub.notifyDataSetChanged();
//                arrayList.clear();
                }
            });

            RecyclerView.LayoutManager l = new GridLayoutManager(this, 1);


            recyclerView.setLayoutManager(l);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adaptar_sub);
        } else {
            Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_LONG).show();
        }

    }
}