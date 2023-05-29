package com.example.student_task;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    Button update;
    EditText topic;
    String name;
    int index;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        update=findViewById(R.id.updateButton);
        topic=findViewById(R.id.updateTopic);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            name=bundle.getString("title");
            index=MainActivity.datalist.indexOf(name);
            topic.setText(bundle.getString("title"));
        }

        update.setOnClickListener(view -> {
            name=topic.getText().toString().trim();
            MainActivity.datalist.set(index,name);
            Toast.makeText(UpdateActivity.this,"Updated successfully",Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}