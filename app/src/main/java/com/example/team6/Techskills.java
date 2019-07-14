package com.example.team6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Techskills extends AppCompatActivity {

    private ImageButton mlb,bigb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_techskills);
        mlb=(ImageButton)findViewById(R.id.mlimage);
        bigb=(ImageButton)findViewById(R.id.bigdataimg);
        mlb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Techskills.this,ml.class);
                startActivity(intent);

            }
        });
    }
}
