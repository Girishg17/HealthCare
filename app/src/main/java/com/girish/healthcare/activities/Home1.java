package com.girish.healthcare.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Handler;

import com.girish.healthcare.R;
import com.girish.healthcare.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setupActionBar();

        CardView card_view = (CardView) findViewById(R.id.calculate_bmi);
        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home1.this, BmiActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupActionBar() {
        Toolbar toolbar_main_activity = (Toolbar) findViewById(R.id.toolbar_main_activity);
        setSupportActionBar(toolbar_main_activity);
        toolbar_main_activity.setNavigationIcon(R.drawable.ic_action_navigation_menu);

        toolbar_main_activity.setNavigationOnClickListener(v -> {
            toggleDrawer();
        });

    }

    private void toggleDrawer() {
        DrawerLayout drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            drawer_layout.openDrawer(GravityCompat.START);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            doubleBackToExit();
        }
    }

    void doubleBackToExit() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(
                this, "please press  again to exit"
                ,
                Toast.LENGTH_SHORT
        ).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        switch (item.getItemId()) {
            case R.id.nav_my_profile:
                Toast.makeText(
                        this, "my profile clicked"
                        ,
                        Toast.LENGTH_SHORT
                ).show();
                //startActivity(new Intent(MainActivity.this, MyProfileActivity.class));
                break;
            case R.id.nav_signout:
                // Here sign outs the user from firebase in this device.
                FirebaseAuth.getInstance().signOut();

                // Send the user to the intro screen of the application.
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }
        drawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateNavigationUserDetails(User loggedInUser) {
    }
}