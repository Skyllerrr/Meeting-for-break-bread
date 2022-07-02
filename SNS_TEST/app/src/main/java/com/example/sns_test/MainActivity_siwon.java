package com.example.sns_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity_siwon extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private HomeFragment_siwon homeFragmentSiwon = new HomeFragment_siwon();
    private FriendFragment_siwon friendFragmentSiwon = new FriendFragment_siwon();
    private UserFragment_siwon userFragmentSiwon = new UserFragment_siwon();
    private DatabaseReference mDatabase;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.
    Bundle bundle;
    Bundle bundle1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
//////////////////////////////////////////////////
        Intent userdataIntent=getIntent();
        String email_data=userdataIntent.getStringExtra("email");

        Log.v("test1","email intent1 : "+email_data);

//////////////////////////////////////////////////
        bundle =new Bundle();
        bundle.putString("email_data",email_data);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        homeFragmentSiwon.setArguments(bundle);
        transaction.replace(R.id.home_ly, homeFragmentSiwon).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.Smenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

        mDatabase = FirebaseDatabase.getInstance().getReference();

        ////////////////////////////////////


        //////////////////////////////////
        Intent userdataIntent1=getIntent();
        String email_data1=userdataIntent1.getStringExtra("email");

        Log.v("test","email : "+email_data1);

        bundle1 =new Bundle();
        bundle1.putString("email_data1",email_data1);

    }


    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Log.v("test3", "logout");
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            Log.v("test3", "test");
            //findViewById(R.id.logout).setOnClickListener(onClickListener);
            switch(menuItem.getItemId())
            {
                case R.id.tab1:
                    transaction.replace(R.id.home_ly, homeFragmentSiwon).commitAllowingStateLoss();
                    break;
                case R.id.tab2:
                    //myStartActivity(MapActivity_sangheon.class);
                    transaction.replace(R.id.home_ly, friendFragmentSiwon).commitAllowingStateLoss();
                    break;
                case R.id.tab4:
                    userFragmentSiwon.setArguments(bundle1);
                    transaction.replace(R.id.home_ly, userFragmentSiwon).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}