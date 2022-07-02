package com.example.sns_test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class data_list_seho extends AppCompatActivity{
    public static Context mContext;
    String name;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_list_seho);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mContext=this;
        Intent userdataIntent1=getIntent();
        String email=userdataIntent1.getStringExtra("email");

        String username=userdataIntent1.getStringExtra("nickname");
        name=userdataIntent1.getStringExtra("Name");

        TextView TextViewName=(TextView) findViewById(R.id.TextViewName);
        TextViewName.setText(name);
        Button btn_back=findViewById(R.id.btn_back);

        ArrayList<booking_seho> list = new ArrayList<>();
        ListView listView = findViewById(R.id.listView);
        final adapter_seho adapter_seho = new adapter_seho(data_list_seho.this, list);
        listView.setAdapter(adapter_seho);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("예약중").child(name).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                booking_seho booking_seho = snapshot.getValue(booking_seho.class);

                list.add(new booking_seho(name,booking_seho.getTitle(), booking_seho.getCurrent_number(), booking_seho.getNumber(), booking_seho.getTime(),email));
                listView.setAdapter(adapter_seho);

            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void onBackButtonClicked(View view) {

        Toast.makeText(this,"onBackButtonClicked",Toast.LENGTH_SHORT).show();
        finish();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public void myStartActivitylogin(Class c,String email) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("email",email);

        startActivity(intent);
    }

}
