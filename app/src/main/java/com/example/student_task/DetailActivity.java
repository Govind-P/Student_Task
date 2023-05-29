package com.example.student_task;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    TextView title;
    Button update,delete;
    String name;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        title=findViewById(R.id.titledetail);
        update=findViewById(R.id.editButton);
        delete=findViewById(R.id.delButton);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            name=bundle.getString("title");
            index=MainActivity.datalist.indexOf(name);
        }
        update.setOnClickListener(view -> {
            Intent i=new Intent(DetailActivity.this,UpdateActivity.class);
            i.putExtra("title",MainActivity.datalist.get(index));
            startActivity(i);
        });
        delete.setOnClickListener((android.view.View view) -> {
            new AlertDialog.Builder(DetailActivity.this)
                    .setTitle("Do you want to remove "+name+ " from the list?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MainActivity.datalist.remove(index);
                            MainActivity.statuslist.remove(index);
                            TaskFragment.adapter.notifyDataSetChanged();
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
        });
    }
    protected void onResume(){
        super.onResume();
        refreshActivity();
    }

    private void refreshActivity() {
        title = findViewById(R.id.titledetail);
        title.setText(MainActivity.datalist.get(index));
    }
}