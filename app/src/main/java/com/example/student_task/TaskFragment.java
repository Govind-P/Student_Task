package com.example.student_task;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class TaskFragment extends Fragment {
    FloatingActionButton fab,filter;

    ImageView imageView;
    TextView tv;
    Chip chip1,chip2,chip3;
    int c1=0,c2=0,c3=0;
    RecyclerView rv;
    Button apply;
    static TaskAdapter adapter;



    public TaskFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_task, container, false);
        fab=v.findViewById(R.id.fab);
        chip1=v.findViewById(R.id.chip1);
        chip2=v.findViewById(R.id.chip2);
        chip3=v.findViewById(R.id.chip3);
        filter=v.findViewById(R.id.filter);
        tv=v.findViewById(R.id.textView7);
        imageView=v.findViewById(R.id.imageView1);
        fab.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(),AddActivity.class));
        });
        filter.setOnClickListener(view -> {
            //startActivity(new Intent(getActivity(),FilterActivity.class));
            showCustomDialog();
        });
        rv=v.findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),1);
        rv.setLayoutManager(gridLayoutManager);
        adapter=new TaskAdapter(getActivity(),MainActivity.datalist);
        rv.setAdapter(adapter);
        if(MainActivity.datalist.isEmpty()){
            tv.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
        }
        return v;
    }
    public void onResume() {
        super.onResume();
        c1=0;
        c2=0;
        c3=0;
        chip1.setVisibility(View.INVISIBLE);
        chip2.setVisibility(View.INVISIBLE);
        chip3.setVisibility(View.INVISIBLE);
        if(MainActivity.datalist.isEmpty()){
            tv.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
        }
        else{
            tv.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            adapter=new TaskAdapter(getActivity(),MainActivity.datalist);
            rv.setAdapter(adapter);
        }
        if(!MainActivity.datefilter.equals("")){
            chip1.setText(MainActivity.datefilter);
            chip1.setVisibility(View.VISIBLE);
            c1=1;
        }
        if((MainActivity.priofilter.equals("High") || MainActivity.priofilter.equals("Low")) && c1==0){
            chip1.setText(MainActivity.priofilter);
            chip1.setVisibility(View.VISIBLE);
            c1=1;
        } else if (MainActivity.priofilter.equals("High") || MainActivity.priofilter.equals("Low")) {
            chip2.setText(MainActivity.priofilter);
            chip2.setVisibility(View.VISIBLE);
            c2=1;
        }
        if((MainActivity.statusfilter.equals("Completed") || MainActivity.statusfilter.equals("Pending")) && c1==0){
            chip1.setText(MainActivity.statusfilter);
            chip1.setVisibility(View.VISIBLE);
            c1=1;
        } else if ((MainActivity.statusfilter.equals("Completed") || MainActivity.statusfilter.equals("Pending")) && c2==0) {
            chip2.setText(MainActivity.statusfilter);
            chip2.setVisibility(View.VISIBLE);
            c2=1;
        }
        else if (MainActivity.statusfilter.equals("Completed") || MainActivity.statusfilter.equals("Pending")) {
            chip3.setText(MainActivity.statusfilter);
            chip3.setVisibility(View.VISIBLE);
        }
    }

    private void showCustomDialog() {
        Dialog customDialog = new Dialog(getActivity());
        customDialog.setContentView(R.layout.filter);
        Button apply = customDialog.findViewById(R.id.apply);
        ChipGroup prio,status;
        CheckBox fdue,fprio,fstatus,high,low,complete,pending;
        TextView tv;
        high=customDialog.findViewById(R.id.high);
        low=customDialog.findViewById(R.id.low);
        complete=customDialog.findViewById(R.id.complete);
        pending=customDialog.findViewById(R.id.pending);
        tv=customDialog.findViewById(R.id.selectdate);
        fdue=customDialog.findViewById(R.id.fdue);
        fprio=customDialog.findViewById(R.id.fprio);
        fstatus=customDialog.findViewById(R.id.fstatus);
        prio=customDialog.findViewById(R.id.chipgroupprio);
        status=customDialog.findViewById(R.id.chipgroupstatus);
        if(MainActivity.priofilter.equals("High")){
            fprio.setChecked(true);
            high.setChecked(true);
        }
        else if(MainActivity.priofilter.equals("Low")){
            fprio.setChecked(true);
            low.setChecked(true);
        }
        if(MainActivity.statusfilter.equals("Completed")){
            fstatus.setChecked(true);
            complete.setChecked(true);
        }
        else if(MainActivity.statusfilter.equals("Pending")){
            fstatus.setChecked(true);
            pending.setChecked(true);
        }
        if(MainActivity.datefilter.equals("")){
            fdue.setChecked(false);
            tv.setText("Select Date");
        }
        else{
            fdue.setChecked(true);
            tv.setText(MainActivity.datefilter);
        }
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResume();
                customDialog.dismiss();
            }
        });
        fprio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    prio.setVisibility(View.VISIBLE);
                    prio.setClickable(true);
                    status.setVisibility(View.INVISIBLE);
                    status.setClickable(false);
                    tv.setVisibility(View.INVISIBLE);
                    tv.setClickable(false);
                    low.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                high.setChecked(false);
                                MainActivity.priofilter="Low";
                            } else {
                                MainActivity.priofilter="";
                            }
                        }
                    });
                    high.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                low.setChecked(false);
                                MainActivity.priofilter="High";
                            } else {
                                MainActivity.priofilter="";
                            }
                        }
                    });
                } else {
                    high.setChecked(false);
                    low.setChecked(false);
                    MainActivity.priofilter="";
                    prio.setVisibility(View.INVISIBLE);
                }
            }
        });
        fstatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    prio.setVisibility(View.INVISIBLE);
                    prio.setClickable(false);
                    status.setVisibility(View.VISIBLE);
                    prio.setClickable(true);
                    tv.setVisibility(View.INVISIBLE);
                    tv.setClickable(false);
                    complete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                pending.setChecked(false);
                                MainActivity.statusfilter="Completed";
                            } else {
                                MainActivity.statusfilter="";
                            }
                        }
                    });
                    pending.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                complete.setChecked(false);
                                MainActivity.statusfilter="Pending";
                            } else {
                                MainActivity.statusfilter="";
                            }
                        }
                    });
                } else {
                    complete.setChecked(false);
                    pending.setChecked(false);
                    status.setVisibility(View.INVISIBLE);
                    MainActivity.statusfilter="";
                }
            }
        });
        fdue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    prio.setVisibility(View.INVISIBLE);
                    prio.setClickable(false);
                    status.setVisibility(View.INVISIBLE);
                    prio.setClickable(false);
                    tv.setVisibility(View.VISIBLE);
                    tv.setClickable(true);
                    final Calendar calendar = Calendar.getInstance();
                    final int year = calendar.get(Calendar.YEAR);
                    final int month = calendar.get(Calendar.MONTH);
                    final int day = calendar.get(Calendar.DAY_OF_MONTH);
                    tv.setOnClickListener(view -> {
                        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month = month+1;
                                String date ="";
                                if(month<10){
                                    date = dayOfMonth+"/"+"0"+month+"/"+year;
                                }
                                else{
                                    date = dayOfMonth+"/"+month+"/"+year;
                                }

                                tv.setText(date);
                                MainActivity.datefilter=tv.getText().toString();
                            }
                        },year, month,day);
                        dialog.show();
                    });
                } else {
                    MainActivity.datefilter="";
                    tv.setText("Select Date");
                    tv.setVisibility(View.INVISIBLE);
                }
            }
        });
        customDialog.show();
    }

}