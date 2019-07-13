package com.example.team6.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.team6.R;

public class JobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtCompanyName, txtJobDescription, txtRole,txtSkillsRequired,txtwebsite;
    public ImageView imageView;
    public ItemClickListener listner;


    public JobViewHolder(View itemView)
    {
        super(itemView);


        txtCompanyName = (TextView) itemView.findViewById(R.id.company_name);
        txtRole = (TextView) itemView.findViewById(R.id.role);
        txtSkillsRequired = (TextView) itemView.findViewById(R.id.skills_required);
        txtJobDescription=(TextView) itemView.findViewById(R.id.job_description);
        txtwebsite=(TextView) itemView.findViewById(R.id.website);
    }

    public void setItemClickListner(ItemClickListener listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}
