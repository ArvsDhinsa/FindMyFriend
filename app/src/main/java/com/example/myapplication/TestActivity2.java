package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.databinding.ActivityTest2Binding;

public class TestActivity2 extends BaseActivity {

    ActivityTest2Binding activityTest2Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTest2Binding = ActivityTest2Binding.inflate(getLayoutInflater());
        setContentView(activityTest2Binding.getRoot());
        allocateActivity("Test2");
    }
}