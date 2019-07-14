package com.example.team6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class quizzes extends AppCompatActivity {

    private Button gdb,ml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);
        gdb=(Button)findViewById(R.id.gdbtn);
        ml=(Button)findViewById(R.id.mlbtn);

        ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(quizzes.this,mlquiz.class);
                startActivity(intent);
            }
        });
        gdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(quizzes.this,gdquiz.class);
                startActivity(intent);
            }
        });
    }
}
