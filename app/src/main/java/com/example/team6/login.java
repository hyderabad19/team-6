package com.example.team6;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name=(EditText)findViewById(R.id.etName);
        Password=(EditText)findViewById(R.id.etPasswordEmail);
        Login=(Button)findViewById(R.id.btnLogout);
        userRegistration=(TextView)findViewById(R.id.tvRegister);
        forgotPassword=(TextView)findViewById(R.id.tvForgotPassword);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null) {
            finish();
            startActivity(new Intent(login.this, MainActivity.class));
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(),Password.getText().toString());

            }
        });
        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,registrationActivity.class));
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,password.class));
            }
        });



    }
    private void validate(String userName,String userPassword)
    {
        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(login.this,MainActivity.class));
                    Toast.makeText(login.this,"Login Successful",Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(login.this,"Login Failed",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

}