package com.example.student_task;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationDetails extends AppCompatActivity {
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);
        title=findViewById(R.id.titledetailnoti);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            title.setText(bundle.getString("title"));
        }
    }
}