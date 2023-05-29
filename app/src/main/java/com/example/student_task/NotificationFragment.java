package com.example.student_task;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationFragment extends Fragment {
    
   RecyclerView rv;
   static ImageView imageView;
   static TextView tv;
   static NotificationAdapter adapter;

    public NotificationFragment() {
        // Required empty public constructor
    }
    
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_notification, container, false);
        rv=v.findViewById(R.id.rv);
        tv=v.findViewById(R.id.Nonoti);
        imageView=v.findViewById(R.id.imagenoti);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),1);
        rv.setLayoutManager(gridLayoutManager);
        adapter=new NotificationAdapter(getActivity(),MainActivity.notilist);
        rv.setAdapter(adapter);
        if(MainActivity.notilist.isEmpty()){
            tv.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
        }
        return v;
    }

    public void onResume() {
        super.onResume();

        if(MainActivity.notilist.isEmpty()){
            tv.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
        }
        else{
            tv.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            adapter=new NotificationAdapter(getActivity(),MainActivity.notilist);
            rv.setAdapter(adapter);
        }
    }
}