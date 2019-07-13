package com.example.team6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team6.Model.Job;
import com.example.team6.ViewHolder.JobViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.Query;

public class Jobs extends AppCompatActivity {

    private DatabaseReference ProductsRef;
    private Query query;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Admin/Jobs");
       // query=ProductsRef.equalTo("Jobs");
        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Job> options =
                new FirebaseRecyclerOptions.Builder<Job>()
                        .setQuery(ProductsRef, Job.class)
                        .build();


        FirebaseRecyclerAdapter<Job, JobViewHolder> adapter =
                new FirebaseRecyclerAdapter<Job, JobViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull JobViewHolder holder, int position, @NonNull final Job model)
                    {
                        holder.txtCompanyName.setText(model.getCompanyname());
                        holder.txtJobDescription.setText(model.getJobdescription());
                        holder.txtRole.setText("Role = " + model.getRole() );
                        holder.txtSkillsRequired.setText("Skills Required: "+ model.getSkillsrequired());
                        holder.txtwebsite.setText("Website: "+model.getWebsite());

                    }

                    @NonNull
                    @Override
                    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item_layout, parent, false);
                        JobViewHolder holder = new JobViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
