package com.example.team6;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    RecyclerView recyclerView;
    Context context;
    ArrayList<String> items=new ArrayList<>();
    ArrayList<String > urls=new ArrayList<>();

    public void update(String name,String url){
        items.add(name);
        urls.add(url);
        notifyDataSetChanged();
    }

    public MyAdapter(RecyclerView recyclerView, Context context, ArrayList<String> items, ArrayList<String> urls) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.items = items;
        this.urls=urls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameoffile.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameoffile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameoffile=itemView.findViewById(R.id.nameoffile);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=recyclerView.getChildLayoutPosition(view);
                    //Uri ur=Uri.parse("http://www.google.");
                    Intent i=new Intent(Intent.ACTION_VIEW);
                  //  i.setType("application/pdf");
                    i.setData(Uri.parse(urls.get(position)));
                    context.startActivity(i);

                }
            });
        }
    }
}
