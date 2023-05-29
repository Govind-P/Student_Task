package com.example.student_task;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    ImageView timedue,notifydate;
    EditText topic,desc,sub,priority;
    Button save;
    TextView duetime,duedate;
    private MaterialTimePicker timePicker;
    private MaterialDatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        duetime=findViewById(R.id.Duetime);
        duedate=findViewById(R.id.Duedate);
        timedue=findViewById(R.id.notifytimedue);
        notifydate=findViewById(R.id.notifydate);
        topic=findViewById(R.id.uploadTopic);
        desc=findViewById(R.id.uploadDesc);
        sub=findViewById(R.id.uploadsub);
        priority=findViewById(R.id.priority);
        save=findViewById(R.id.saveButton);

        timedue.setOnClickListener(view -> {
            timePicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setHour(12)
                    .setMinute(0)
                    .setTitleText("Select Notify time")
                    .build();
            timePicker.show(getSupportFragmentManager(), "androidknowledge");
            timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    duetime.setText(timePicker.getHour()+":" + timePicker.getMinute());
                }
            });
        });
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        notifydate.setOnClickListener(view -> {
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
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

                    duedate.setText(date);
                }
            },year, month,day);
            dialog.show();
        });
        save.setOnClickListener(view -> {
            String Topic=topic.getText().toString().trim();
            String Desc=desc.getText().toString().trim();
            String Sub=sub.getText().toString().trim();
            String Prio=priority.getText().toString().trim();
            String dueDate=duedate.getText().toString().trim();
            String dueTime=duetime.getText().toString().trim();
            String status="Pending";
            if(dueTime.equals("")){
                dueTime="23:59";
            }
            if(Topic.equals("") || Desc.equals("") || Sub.equals("") || Prio.equals("") || dueDate.equals("")){
                Toast.makeText(AddActivity.this,"Enter data in all fields",Toast.LENGTH_SHORT).show();
            }
            else {
                //save data into database
                MainActivity.datalist.add(Topic);
                MainActivity.statuslist.add("Pending");
                Toast.makeText(AddActivity.this,"Uploaded Successfully",Toast.LENGTH_SHORT).show();
                TaskFragment.adapter.notifyDataSetChanged();
                finish();
            }
        });
    }
}