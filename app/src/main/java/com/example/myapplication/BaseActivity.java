package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener { // Base Activity allows for all other activities to be added to navigation

    DrawerLayout drawerLayout; // Calls the drawer layout function to be used within this class

    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer); // Creates a container for the activity to extend other activities
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolBar); // Displays the toolbar which holds the navigation menu
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this); // Calls the navigation view which displays the navigation menu

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.menu_drawer_open, R.string.menu_drawer_close);
        drawerLayout.addDrawerListener(toggle); // Allows for the opening and closing of the navigation menu on the activities
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if (item.getItemId() == R.id.nav_currentlocation) {
            startActivity(new Intent(this, MapsActivity2.class)); // If current location option is selected, user is navigated to that page
            overridePendingTransition(0, 0);
        } else if (item.getItemId() == R.id.nav_sharelocation) {
            startActivity(new Intent(this, ChatActivity.class)); // If location sharing option is selected, user is navigated to that page
            overridePendingTransition(0, 0 );
        } else if (item.getItemId() == R.id.nav_chats) {
            startActivity(new Intent(this, TestActivity.class)); // If chats option is selected, user is navigated to that page
            overridePendingTransition(0, 0 );
        }
        return false;
    }

    protected void allocateActivity(String titleString) { // Allocates each activity to part of the nav_menu
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titleString);
        }
    }
}