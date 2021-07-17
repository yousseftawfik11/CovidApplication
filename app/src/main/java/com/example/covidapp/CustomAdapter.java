package com.example.covidapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList<String> username, name, dob, phoneNumber, role,id;
    Animation translate_anim;


    CustomAdapter(Activity activity,
                  Context context,
                  ArrayList username,
                  ArrayList name,
                  ArrayList dob,
                  ArrayList phoneNumber,
                  ArrayList role,
                  ArrayList id
    ){

        this.activity = activity;
        this.context = context;
        this.username = username;
        this.name = name;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.id = id;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //this.position = position;

        holder.username_txt.setText(String.valueOf(username.get(position)));
        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.dob_txt.setText(String.valueOf(dob.get(position)));
        holder.phoneNum_txt.setText(String.valueOf(phoneNumber.get(position)));
        holder.role_txt.setText(String.valueOf(role.get(position)));
        holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, updateActivity.class);
                intent.putExtra("username",String.valueOf(username.get(position)));
                intent.putExtra("name",String.valueOf(name.get(position)));
                intent.putExtra("dob",String.valueOf(dob.get(position)));
                intent.putExtra("phonenum",String.valueOf(phoneNumber.get(position)));
                intent.putExtra("role",String.valueOf(role.get(position)));
                intent.putExtra("id",String.valueOf(id.get(position)));


                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return username.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView username_txt, name_txt, gender_txt, dob_txt, phoneNum_txt, role_txt, id_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username_txt = itemView.findViewById(R.id.username);
            name_txt = itemView.findViewById(R.id.name);
            dob_txt = itemView.findViewById(R.id.dob);
            phoneNum_txt = itemView.findViewById(R.id.phoneNum);
            role_txt = itemView.findViewById(R.id.role);
            id_txt = itemView.findViewById(R.id.user_id);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            translate_anim = AnimationUtils.loadAnimation(context,R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);

        }
    }
}
