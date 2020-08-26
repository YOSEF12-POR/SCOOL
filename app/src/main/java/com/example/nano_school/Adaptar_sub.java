package com.example.nano_school;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class Adaptar_sub extends RecyclerView.Adapter<Adaptar_sub.View_holder_sub> {
    ArrayList<Subject> arrayList;
    Context context;
    OnClckSub onClckSub;

   // Adaptar_sub adaptar_sub;

    public Adaptar_sub(ArrayList<Subject> arrayList, Context context, OnClckSub onClckSub) {
        this.onClckSub = onClckSub;
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public View_holder_sub onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.subjects, null, false);
        View_holder_sub view_holder_sub = new View_holder_sub(v);


        return view_holder_sub;
    }

    @Override
    public void onBindViewHolder(@NonNull View_holder_sub holder, int position) {
        holder.tv_id.setText(arrayList.get(position).getId_sub() + "");
        holder.tv_name.setText(arrayList.get(position).getName_sub());
        holder.tv_std_count.setText(arrayList.get(position).getNumber_std() + "");
        holder.tv_name_doctor.setText(arrayList.get(position).getName_doc());

        holder.subject = arrayList.get(position);


        //     notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class View_holder_sub extends RecyclerView.ViewHolder {
        TextView tv_id;
        TextView tv_name;
        TextView tv_name_doctor;
        TextView tv_std_count;
        Subject subject;

        public View_holder_sub(@NonNull final View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.subjects_tv_id);
            tv_name = itemView.findViewById(R.id.subjects_tv_name);
            tv_std_count = itemView.findViewById(R.id.subjects_tv_number_std);
            tv_name_doctor = itemView.findViewById(R.id.subjects_tv_name_doc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    arrayList.remove(subject);


                    onClckSub.OnCLick(subject);


                }


            });

        }
    }

}
