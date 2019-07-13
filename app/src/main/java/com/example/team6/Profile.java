package com.example.team6;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class Profile extends AppCompatActivity {

    Button selectfile,upload,fetch;
    TextView notification;
    Uri pdfUri;
    String downloadurl,string_name,string_email,string_phone;
    ProgressDialog progressDialog;
    EditText name,email,phone;

    FirebaseStorage storage;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name=(EditText) findViewById(R.id.name);
        email=(EditText) findViewById(R.id.email);
        phone=(EditText) findViewById(R.id.phone);

        storage=FirebaseStorage.getInstance();
        database= FirebaseDatabase.getInstance();

        selectfile=findViewById(R.id.selectFile);
        upload=findViewById(R.id.upload);
        notification=findViewById(R.id.notification);
       // fetch=findViewById(R.id.fetchFile);

      /*  fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Profile.this,View2.class));
            }
        });*/

        selectfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(Profile.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    selectPdf();
                }
                else{
                    ActivityCompat.requestPermissions(Profile.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                }
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdfUri!=null)
                    uploadFile(pdfUri);
                else
                    Toast.makeText(Profile.this,"Select a File",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadFile(Uri pdfUri) {

        string_email=email.getText().toString();
        string_name=name.getText().toString();
        string_phone=phone.getText().toString();

        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("Uploading file...");
        progressDialog.setProgress(0);
        progressDialog.show();
        String name=pdfUri.getLastPathSegment();
        name=name.substring(name.indexOf("/"),name.lastIndexOf("."));
        final String fileName = name+".pdf";
        final String fileName1=name+"";
        final StorageReference storageReference = storage.getReference().child("Trainee").child("Profile").child(fileName);
        storageReference.putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                downloadurl=uri.toString();
                                uploaddatabase(downloadurl);
                            }
                        });

                    }

                    private void uploaddatabase(String downloadurl) {
                        final DatabaseReference reference=database.getReference();
                        reference.child("Trainee").child("Profile").child(fileName1).setValue(downloadurl)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {

                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Profile.this, "File successfully uploaded", Toast.LENGTH_SHORT).show();
                                            HashMap<String, Object> data=new HashMap<>();
                                            data.put("Name",string_name);
                                            data.put("Email",string_email);
                                            data.put("Phone Number",string_phone);
                                            reference.child("Trainee").child("Profile").updateChildren(data);
                                            progressDialog.dismiss();

                                            Intent i = new Intent(Profile.this, MainActivity.class);
                                            startActivity(i);
                                            finish();
                                        } else {
                                            Toast.makeText(Profile.this, "File not successfully uploaded", Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                            Intent i = new Intent(Profile.this, MainActivity.class);
                                            startActivity(i);
                                            finish();
                                        }
                                    }
                                });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Profile.this,"File not successfully uploaded",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                Intent i = new Intent(Profile.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                int currentProgress=(int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectPdf();
        }
        else {
            Toast.makeText(Profile.this,"Please give permission",Toast.LENGTH_SHORT).show();
        }
    }

    private void selectPdf() {
        Intent i=new Intent();
        i.setType("application/pdf");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 86 && resultCode == RESULT_OK && data != null) {
            pdfUri=data.getData();
            notification.setText("A File is selected : "+data.getData().getLastPathSegment());
        }
        else{
            Toast.makeText(Profile.this,"Please Select a File",Toast.LENGTH_SHORT).show();
        }
    }
}
