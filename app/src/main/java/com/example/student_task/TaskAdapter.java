package com.example.student_task;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<MyViewHolder> {
    public TaskAdapter(Context context, List<String> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    private Context context;
    private List<String> datalist;



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(datalist.get(position));
        if(MainActivity.statuslist.get(position).equals("Completed")){
            holder.title.setTextColor(Color.RED);
            holder.cb.setChecked(true);
        }
        holder.recCard.setOnClickListener(view -> {
            Intent intent=new Intent(context, DetailActivity.class);
            intent.putExtra("title",datalist.get(holder.getAdapterPosition()));
            context.startActivity(intent);
        });
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    new AlertDialog.Builder(context)
                            .setTitle("Do you complete "+datalist.get(position)+ " ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    holder.title.setTextColor(Color.RED);
                                    MainActivity.statuslist.set(position,"Completed");
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    holder.cb.setChecked(false);
                                    dialogInterface.dismiss();
                                }
                            }).create().show();

                }
                else{
                    new AlertDialog.Builder(context)
                            .setTitle("Do you have pending work on "+datalist.get(position)+ " ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    holder.title.setTextColor(Color.BLACK);
                                    MainActivity.statuslist.set(position,"Pending");
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    holder.cb.setChecked(true);
                                    dialogInterface.dismiss();
                                }
                            }).create().show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    TextView title,status,due;
    CheckBox cb;
    CardView recCard;
    public MyViewHolder(@NonNull View itemView){
        super(itemView);
        title=itemView.findViewById(R.id.recTitle);
        cb=itemView.findViewById(R.id.cb);
        recCard=itemView.findViewById(R.id.reccard);
    }
}