package com.example.sns_test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class UserFragment_siwon extends Fragment {

    EditText email, name;


    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.

    private DatabaseReference mDatabase;
    private Object MainActivity;

    private  final int GALLERY_CODE = 10;
    ImageView photo;
    private FirebaseStorage storage;
    Bitmap img;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_usersetting, container, false);

        photo= (ImageView) v.findViewById(R.id.profileImg);
        storage=FirebaseStorage.getInstance();
        photo.setImageBitmap(img);
        Button changebutton=v.findViewById(R.id.changebutton);
        Button logout=v.findViewById(R.id.logout);
        Button writebutton=v.findViewById(R.id.writebutton);
        Log.v("fragment","test1");

        setHasOptionsMenu(true);

        Bundle extra=this.getArguments();

        if(extra != null) {
            extra = getArguments();

            String extra_mail = extra.getString("email_data1");
            Log.v("test","extra_email : "+extra_mail);

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    readUser(extra_mail);
                }
            }, 10);

        }

        mDatabase = FirebaseDatabase.getInstance().getReference(); //DatabaseReference의 인스턴스
        name = (EditText) v.findViewById(R.id.name);
        email = (EditText)v.findViewById(R.id.email);

        changebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("fragment","test2");
                loadAlbum();
            }
        });

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.v("test3","test");
                myStartActivity(LoginActivity.class);
            }
        });

        writebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emaildata=email.getText().toString();
                String namedata=name.getText().toString();
                writeNewUser(emaildata,namedata);
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    private void loadAlbum() {
        Log.v("fragment","test3");
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, GALLERY_CODE);
    }


    public void readUser(String userId) {
        Log.v("test", "test1");
        //데이터 읽기

        mDatabase.child("users").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userdata = dataSnapshot.getValue(User.class);
                String username=userdata.getName();
                String useremail=userdata.getEmail();
                name.setText(username);
                email.setText(useremail);

                Log.v("test4", "data change");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { //참조에 액세스 할 수 없을 때 호출
                Log.v("test", "fail read");
            }
        });
    }
    public void writeNewUser(String email, String name) {
        User user = new User(name,email);

        mDatabase.child("users").child(name).setValue(user);
    }




    @Override
    public void onActivityResult(int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CODE) {
            Uri file = data.getData();
            StorageReference storageRef = storage.getReference();
            StorageReference riversRef = storageRef.child("photo/1.png");
            UploadTask uploadTask = riversRef.putFile(file);

            try {
                InputStream in = getActivity().getContentResolver().openInputStream(data.getData());
                img = BitmapFactory.decodeStream(in);
                in.close();
                Log.v("fragment","test4");
                photo.setImageBitmap(img);
            } catch (Exception e) {
                e.printStackTrace();
            }

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(getActivity(), "사진이 정상적으로 업로드 되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                }
            });
        }
    }
    private void myStartActivity(Class c) {
        Intent intent = new Intent(getActivity(),c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}