package com.example.team6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Softskills extends AppCompatActivity {
    private ImageView gpb,jmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_softskills);
        gpb=(ImageButton)findViewById(R.id.gd);
        gpb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Softskills.this,gd.class);
                startActivity(intent);
            }
        });
    }
}
