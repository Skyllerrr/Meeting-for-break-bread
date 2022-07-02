package com.example.sns_test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity_seho extends AppCompatActivity {

    Button sub_count, add_count, btn_true, btn_false;
    TextView count, tv_time,TextViewName;
    EditText title;
    TimePicker timePicker;

    int count_number = 0;


    /////////////////////////인텐트 값 받아옴

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_seho);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent userdataIntent1=getIntent();
        String username=userdataIntent1.getStringExtra("nickname");
        String name=userdataIntent1.getStringExtra("Name");
        String email=userdataIntent1.getStringExtra("email");


        setTitle("방파기");


        sub_count = (Button) findViewById(R.id.sub_count);
        add_count = (Button) findViewById(R.id.add_count);
        btn_true = (Button) findViewById(R.id.btn_true);
        btn_false = (Button) findViewById(R.id.btn_false);
        count = (TextView) findViewById(R.id.count);
        tv_time = (TextView) findViewById(R.id.tv_time);
        title = (EditText) findViewById(R.id.title);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        TextViewName = (TextView) findViewById(R.id.TextViewName);



        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                if (hour > 12){
                    hour -= 12;
                    tv_time.setText("오후" + hour + "시" + minute + "분");
                }
                else{
                    tv_time.setText("오전" + hour + "시" + minute + "분");
                }
            }
        });

        add_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_number++;
                count.setText(count_number+"");
            }
        });

        sub_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count_number>0){
                    count_number--;
                    count.setText(count_number+"");
                }
            }
        });

        btn_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                booking_seho booking_seho =new booking_seho(name,title.getText().toString()
                        ,"1",
                        count.getText().toString(),
                        tv_time.getText().toString(),
                        email);

                database.child("예약중").child(name).child(title.getText().toString()).setValue(booking_seho);
                database.child("예약완료").child(name).child("email").setValue(name);

                Toast toast = Toast.makeText(MainActivity_seho.this, "방을 만들었습니다",Toast.LENGTH_SHORT);
                toast.show();

            }
        });

        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

}