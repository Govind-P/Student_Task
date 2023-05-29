package com.example.student_task;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.student_task.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    static List<String> notilist;
    static String statusfilter="";
    static String priofilter="";
    static String datefilter="";
    static List<String> datalist;
    static List<String> statuslist;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notilist=new ArrayList<>();
        notilist.add("Notification1");
        notilist.add("Notification2");
        notilist.add("Notification3");
        notilist.add("Notification4");
        notilist.add("Notification5");
        notilist.add("Notification6");
        notilist.add("Notification7");
        notilist.add("Notification8");
        notilist.add("Notification9");
        notilist.add("Notification10");
        datalist=new ArrayList<>();
        statuslist=new ArrayList<>();
        statuslist.add("Pending");
        statuslist.add("Pending");
        statuslist.add("Pending");
        datalist.add("PHN Task 3");
        datalist.add("PHN TASK 5");
        datalist.add("PHN TASK 6");
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new TaskFragment());
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.task:
                    replaceFragment(new TaskFragment());
                    break;
                case R.id.notification:
                    replaceFragment(new NotificationFragment());
                    break;
                case R.id.settings:
                    replaceFragment(new SettingsFragment());
                    break;

            }
            return true;
        });
    }
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

}
