package com.example.sns_test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class SettingsFragment_siwon extends Fragment {

    private DatabaseReference mDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v3= inflater.inflate(R.layout.fragment_settings, container, false);


        readUser();



        return v3;
    }

    public void readUser() {
        Log.v("test", "test1");
        //데이터 읽기
        mDatabase.child("예약완료").child("서브밀").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userdata = dataSnapshot.getValue(User.class);

                String useremail=userdata.getEmail();


                Log.v("test4", useremail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

}