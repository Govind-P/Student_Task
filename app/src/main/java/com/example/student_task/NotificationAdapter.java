package com.example.student_task;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotiViewHolder> {
    public NotificationAdapter(Context context, List<String> notilist) {
        this.context = context;
        this.notilist = notilist;
    }

    private Context context;
    private List<String> notilist;



    @NonNull
    @Override
    public NotiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_notification,parent,false);
        return new NotiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotiViewHolder holder, int position) {
        holder.title.setText(notilist.get(position));
        holder.status.setText("Pending");
        holder.close.setOnClickListener(view -> {
            MainActivity.notilist.remove(position);
            NotificationFragment.adapter.notifyDataSetChanged();
        });
        holder.recCard.setOnClickListener(view -> {
            Intent intent=new Intent(context, NotificationDetails.class);
            intent.putExtra("title",notilist.get(holder.getAdapterPosition()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notilist.size();
    }
}

class NotiViewHolder extends RecyclerView.ViewHolder{
    TextView title,status;
    ImageView close;
    CardView recCard;
    public NotiViewHolder(@NonNull View itemView){
        super(itemView);
        title=itemView.findViewById(R.id.notificationTitle);
        close=itemView.findViewById(R.id.close);
        status=itemView.findViewById(R.id.notificationDesc);
        recCard=itemView.findViewById(R.id.notificationCard);
    }
}