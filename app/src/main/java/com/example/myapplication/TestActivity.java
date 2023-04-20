package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.databinding.ActivityTestBinding;

public class TestActivity extends BaseActivity {

    ActivityTestBinding activityTestBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTestBinding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(activityTestBinding.getRoot());
        allocateActivity("Test");
    }
}