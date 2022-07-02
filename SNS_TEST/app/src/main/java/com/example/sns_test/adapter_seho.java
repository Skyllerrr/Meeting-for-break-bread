package com.example.sns_test;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sns_test.data_list_seho;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class adapter_seho extends BaseAdapter {
    Context context;
    ArrayList<booking_seho> items;
    Integer a = 1;

    public adapter_seho(Context context, ArrayList<booking_seho> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount(){
        return items.size();
    }

    @Override
    public Object getItem(int position){
        return  items.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.in_list_seho,null);
        }
        booking_seho people = (booking_seho) getItem(position);

        TextView in_title = convertView.findViewById(R.id.in_title);
        TextView in_current_number = convertView.findViewById(R.id.in_current_number);
        TextView in_number = convertView.findViewById(R.id.in_number);
        TextView in_time = convertView.findViewById(R.id.in_time);



        in_title.setText(people.getTitle());
        in_current_number.setText(people.getCurrent_number());
        in_number.setText(people.getNumber());
        in_time.setText(people.getTime());



        Button lego_button = (Button) convertView.findViewById(R.id.lego_button);
        lego_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("예약하시겠습니까?");
                builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

                        a = a+1;
                        database.child("예약중").child(people.getName()).child(people.getTitle()).child("current_number").setValue(a.toString());

                        if (a == Integer.valueOf(people.number)){
                            booking_seho booking_seho =new booking_seho(people.getName(),
                                    people.getTitle().toString(),
                                    people.getNumber().toString(),
                                    people.getCurrent_number().toString(),
                                    people.getTime().toString(),
                                    people.getEmail());
                            //((data_list_seho)data_list_seho.mContext).myStartActivitylogin(MainActivity_siwon.class,people.email);
                            database.child("예약완료").child(people.getName()).child("email").setValue((people.getName()));

                            database.child("예약중").child(people.getName()).child(people.title).removeValue();
                            Toast.makeText(context,"모임이 확정되었습니다.\n 시간을 확인해 주세요!!",Toast.LENGTH_SHORT).show();
                            dialogInterface.cancel();
                        }

                        Toast toast = Toast.makeText(context, "예약되었습니다",Toast.LENGTH_SHORT);
                        toast.show();
                        dialogInterface.cancel();
                    }
                });
                builder.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        return convertView;

    }

}