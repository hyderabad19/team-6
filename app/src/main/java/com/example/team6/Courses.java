package com.example.team6;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Courses extends AppCompatActivity {

    private FirebaseAuth mAuth;
     TextView others,techskilltext,softskills,categoriestext;
     ImageView othersimg,techimg,softskillsimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        mAuth = FirebaseAuth.getInstance();

        sk.setOnClickListener(new View.OnClickListener() {

        softskillsimg = (ImageView) findViewById(R.id.softskillsimg);
        techimg = (ImageView) findViewById(R.id.techimg);
        othersimg= (ImageView) findViewById(R.id.othersimg);
        softskillsimg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Courses.this,Softskills.class);
                startActivity(intent);
            }
        });
        techimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Courses.this,Techskills.class);
                startActivity(intent);
            }

        });
        othersimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Courses.this,Otherskills.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }



}
