package com.example.sns_test;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

public class MainActivity_subin extends AppCompatActivity {


    String  sapId, nickname, ustar;
    float gstarn, nuP, cal;
    int nuB;
    int count = 1;
    boolean flag;
    private Button sendbt;
    ScrollView scroll;
    LinearLayout shibal;
    Button shi;
    TextView review, test,textView3,textView;
    RatingBar star, gstar;
    ArrayList list = new ArrayList();
    ArrayList idlist = new ArrayList();
    ImageView imageView2;

    Button button;
    Button button2;
    Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_subin);

        //setTheme();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        /////////////////////////인텐트 값 받아옴

        Intent userdataIntent1=getIntent();
        String username=userdataIntent1.getStringExtra("nickname");
        String email=userdataIntent1.getStringExtra("email");
        int number=userdataIntent1.getIntExtra("number",0);

        ////////////////////////맵 설정
        MapView mapView = new MapView(this);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map);
        mapViewContainer.addView(mapView);

        double[] x = {36.831637,36.830424,36.832779,36.832808,36.833667,36.833022,36.830402,36.832903,};
        double[] y = {127.177179,127.177110,127.175677,127.176270,127.173143,127.175711,127.177311,127.174691};


        int[] imgs={R.drawable.zzazzangmyun,R.drawable.kookbab,R.drawable.submill,
                R.drawable.moyaymoya,R.drawable.cyunhoji, R.drawable.star,
                R.drawable.maratang, R.drawable.buanzib};

        String Name[] = {"흥부반점","명장국밥","서브밀","모야모야", "천호지", "별이네식당", "조가연마라탕", "부안집"};
        textView3=findViewById(R.id.textView3);
        textView3.setText(Name[number]);


        String menu[]={"<메뉴>\n\n짜장면\n짬뽕 \n탕수육\n쟁반짜장\n"
                ,"<메뉴>\n\n수육국밥\n순대국밥\n짬봉수육국밥\n짬뽕순대국밥",
                "<메뉴>\n\n브리또\n점보\n닭, 돼지, 소, 소세지",
                "<메뉴>\n\n스테이크덮밥\n연어덮밥\n모야모야덮밥",
                "<메뉴>\n\n오리훈제\n삼겹살\n오리주물럭",
                "<메뉴>\n\n갈비탕\n김치찌개\n된장찌개",
                "<메뉴>\n\n마라탕\n재료는 가서 고르기",
                "<메뉴>\n\n냉동삼겹살\n목살\n껍대기"};
        textView=findViewById(R.id.textView);
        textView.setText(menu[number]);

        String Pathparent="시원아밥먹자/";
        String Pathchild=Name[number];
        String Path=Pathparent.concat(Pathchild);
        Log.v("test1",Path);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(Path);
        DatabaseReference myStar = firebaseDatabase.getReference(Path.concat("/계산용"));
        DatabaseReference nuPeople = firebaseDatabase.getReference(Path.concat("/누적용"));


        imageView2=findViewById(R.id.imageView2);
        imageView2.setImageResource(imgs[number]);
        // 앱 최초 실행시 중심점 변경 (현 위치 상명대 천안캠퍼스)
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(x[number], y[number]), true);

        //마커
        MapPOIItem marker = new MapPOIItem();

        //배열좌표


        //배열의 크기
        double[] array = new double[8];


        //맵 포인트 위도경도 설정
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(x[number], y[number]);
        marker.setItemName((Name[number]));

        marker.setTag(0);
        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(marker);

        /////////////


        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        review = findViewById(R.id.review);
        star = findViewById(R.id.star);
        gstar = findViewById(R.id.gstar);
        shi = (Button) findViewById(R.id.shi);
        shibal = findViewById(R.id.shibal);
        //////인텐트해서 닉네임 받아서 쓸 부분//////
        nickname = email;  /////예) nickname = 인텐트한 userNickname
        //////인텐트해서 닉네임 받아서 쓸 부분//////
        ustar = "0";


        Log.v("test1","nickname : "+nickname);
        myStar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sv = snapshot.getValue(String.class);
                gstarn = Float.valueOf(sv);
                gstar.setRating(gstarn/(nuP+1));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        nuPeople.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String p = snapshot.getValue(String.class);
                nuP = (Float.valueOf(p));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                shibal.removeAllViews();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String str = ds.getKey();
                    String[] strArr = str.split(">");
                    if ((!str.equals("계산용"))&&(!str.equals("누적용"))){
                        LinearLayout linearLayout = new LinearLayout(MainActivity_subin.this);
                        linearLayout.setOrientation(LinearLayout.VERTICAL);
                        linearLayout.setBackgroundColor(Color.WHITE);


                        TextView textView_01 = new TextView(MainActivity_subin.this);
                        textView_01.setBackgroundColor(Color.GRAY);
                        textView_01.setTextSize(20);
                        textView_01.setText(str+": "+snapshot.child(str).child("0").getValue(String.class));


                        RatingBar rstar = new RatingBar(MainActivity_subin.this);
                        rstar.setNumStars(5);
                        rstar.setRating(Float.valueOf(snapshot.child(str).child("1").getValue(String.class)));
                        rstar.setStepSize(0.5F);
                        rstar.setIsIndicator(TRUE);


                        LinearLayout.LayoutParams layoutLayoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                200
                        );

                        LinearLayout.LayoutParams textViewLayoutParams_01 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT, // layout_width 설정
                                LinearLayout.LayoutParams.WRAP_CONTENT // layout_height 설정

                        );
                        LinearLayout.LayoutParams rstar_layout = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT, // layout_width 설정
                                LinearLayout.LayoutParams.WRAP_CONTENT // layout_height 설정

                        );
                        linearLayout.addView(textView_01, textViewLayoutParams_01);
                        linearLayout.addView(rstar, rstar_layout);

                        shibal.addView(linearLayout, layoutLayoutParams);
                        //linearLayout.;
                    }
                    if(strArr[0].equals(nickname)){
                        String a = snapshot.child(str).child("2").getValue(String.class);
                        nuB = Integer.valueOf(a);
                        Log.d("ee", String.valueOf(nuB));
                        flag = TRUE;
                    }
                    else{
                        flag = FALSE;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("qrq","qq");
            }
        });




        shi.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myReview  = review.getText().toString();
                list.add(myReview);
                ustar = String.valueOf(star.getRating());
                list.add(ustar);

                if (flag==TRUE){
                    nuB=nuB+1;
                    String a = String.valueOf(nuB);
                    list.add(a);
                    String imi = nickname+">";
                    String gonuB = imi + a + "번째 방문손님";
                    Log.d("tt",String.valueOf(nuB));
                    sapId=gonuB;
                }
                if (flag == FALSE) {
                    list.add("1");
                    String imi = nickname+">";
                    String gonuB = imi + list.get(2).toString() + "번째 방문손님";;
                    sapId=gonuB;

                }
                databaseReference.child(sapId).setValue(list);
                list.clear();

                cal = gstarn+Float.valueOf(ustar);
                myStar.setValue(String.valueOf(cal));
                nuPeople.setValue(String.valueOf(nuP+1));

            }
        });


        ///////////////////////////추가부분
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myStartActivity(MainActivity_seho.class,nickname,Name[number],email);
                Log.v("test1","nickname2 : "+Name[number]);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myStartActivity(data_list_seho.class,nickname,Name[number],email);
                Log.v("test1","nickname2 : "+Name[number]);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myStartActivitylogin(MainActivity_siwon.class,email);
                Log.v("test1","nickname3 : "+email);
            }
        });
    }
    private void myStartActivity(Class c,String nickname_data,String Name,String email) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("nickname",nickname_data);
        intent.putExtra("Name",Name);
        intent.putExtra("email",email);
        startActivity(intent);
    }
    private void myStartActivitylogin(Class c,String email) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("email",email);
        startActivity(intent);
    }
}