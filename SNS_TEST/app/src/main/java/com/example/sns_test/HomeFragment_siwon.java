package com.example.sns_test;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;


public class HomeFragment_siwon extends Fragment {
    int flag=0;
    boolean flag2=TRUE;
    String username;
    String useremail;
    String extra_mail;
    RatingBar rating1,rating2,rating3,rating4,rating5,rating6,rating7,rating8;
    private DatabaseReference mDatabase;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v1=inflater.inflate(R.layout.fragment_home, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Log.v("fragment", "main");

        ImageButton imagebutton1=(ImageButton) v1.findViewById(R.id.imagebutton1);
        ImageButton imagebutton2=(ImageButton) v1.findViewById(R.id.imagebutton2);
        ImageButton imagebutton3=(ImageButton) v1.findViewById(R.id.imagebutton3);
        ImageButton imagebutton4=(ImageButton) v1.findViewById(R.id.imagebutton4);
        ImageButton imagebutton5=(ImageButton) v1.findViewById(R.id.imagebutton5);
        ImageButton imagebutton6=(ImageButton) v1.findViewById(R.id.imagebutton6);
        ImageButton imagebutton7=(ImageButton) v1.findViewById(R.id.imagebutton7);
        ImageButton imagebutton8=(ImageButton) v1.findViewById(R.id.imagebutton8);
///////////////////////////////////////////////////////////////////////////////////////////////////rating
        rating1 = v1.findViewById(R.id.rating1);
        rating2 = v1.findViewById(R.id.rating2);
        rating3 = v1.findViewById(R.id.rating3);
        rating4 = v1.findViewById(R.id.rating4);
        rating5 = v1.findViewById(R.id.rating5);
        rating6 = v1.findViewById(R.id.rating6);
        rating7 = v1.findViewById(R.id.rating7);
        rating8 = v1.findViewById(R.id.rating8);

        RatingBar[] ratings={rating1,rating2,rating3,rating4,rating5,rating6,rating7,rating8};


        String Name[] = {"흥부반점","명장국밥","서브밀","모야모야", "천호지", "별이네식당", "조가연마라탕", "부안집"};

        Float gstarn[]={0f,0f,0f,0f,0f,0f,0f,0f};
        Float nuP[]={0f,0f,0f,0f,0f,0f,0f,0f};

        for(int number=0;number<8;number++){
            final  int INDEX;
            INDEX=number;

            String Pathchild=Name[INDEX];
            String Pathparent="시원아밥먹자/";
            String Path=Pathparent.concat(Pathchild);
            Log.v("rating",Path);

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference(Path);
            DatabaseReference myStar = firebaseDatabase.getReference(Path.concat("/계산용"));
            DatabaseReference nuPeople = firebaseDatabase.getReference(Path.concat("/누적용"));



            myStar.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String sv = snapshot.getValue(String.class);
                    gstarn[INDEX] = Float.valueOf(sv);

                    Log.v("rating gstarn", String.valueOf(gstarn));
                    Log.v("rating index", String.valueOf(INDEX));
                    Log.v("rating star",Float.toString((gstarn[INDEX]/(nuP[INDEX]+1))));

                    ratings[INDEX].setRating(gstarn[INDEX]/(nuP[INDEX]));
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            nuPeople.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String p = snapshot.getValue(String.class);
                    nuP[INDEX] = (Float.valueOf(p));
                    Log.v("rating index", String.valueOf(INDEX));
                    Log.v("rating nuP", String.valueOf(nuP[INDEX]));
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    ratings[INDEX].setRating(gstarn[INDEX]/(nuP[INDEX]));
                }
            }, 10);
        }
        //////////////////////////////////////////////////////////////////////////////////////////
        setHasOptionsMenu(true);

        Bundle extra=this.getArguments();

        if(extra != null) {
            extra = getArguments();

            extra_mail = extra.getString("email_data");
            Log.v("test1","extra_email : "+extra_mail);

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Log.v("test","readuserrun");
                    readUser(extra_mail);
                }
            }, 10);

        }

        ///////////

        //////////////
        imagebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("test1", "image"+username);
                myStartActivity(MainActivity_subin.class,username,useremail,0);
            }
        });
        imagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("test1", "image"+extra_mail);
                myStartActivity(MainActivity_subin.class,username,useremail,1);
            }
        });
        imagebutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("test1", "image"+username);
                myStartActivity(MainActivity_subin.class,username,useremail,2);
            }
        });
        imagebutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("test1", "image"+extra_mail);
                myStartActivity(MainActivity_subin.class,username,useremail,3);
            }
        });
        imagebutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("test1", "image"+username);
                myStartActivity(MainActivity_subin.class,username,useremail,4);
            }
        });
        imagebutton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("test1", "image"+extra_mail);
                myStartActivity(MainActivity_subin.class,username,useremail,5);
            }
        });
        imagebutton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("test1", "image"+username);
                myStartActivity(MainActivity_subin.class,username,useremail,6);
            }
        });
        imagebutton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("test1", "image"+extra_mail);
                myStartActivity(MainActivity_subin.class,username,useremail,7);
            }
        });
        // Inflate the layout for this fragment
        return v1;
    }

    private void myStartActivity(Class c,String username,String extra_mail,Integer number) {
        Intent intent = new Intent(getActivity(), c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("nickname",username);
        intent.putExtra("email",extra_mail);
        intent.putExtra("number",number);
        Log.v("test1", "test1"+username);
        startActivity(intent);
    }

    public void readUser(String userId) {
        Log.v("test1", "test1");
        //데이터 읽기

        mDatabase.child("users").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userdata = dataSnapshot.getValue(User.class);
                username=userdata.getName();
                useremail=userdata.getEmail();
                Log.v("test1", "username");
                Log.v("test1", username);
                Log.v("test1", useremail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { //참조에 액세스 할 수 없을 때 호출
                Log.v("test", "fail read");
            }
        });
    }


}