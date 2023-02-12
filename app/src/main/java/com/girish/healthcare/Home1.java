package com.girish.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import android.os.Handler;

public class Home1 extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);
        setupActionBar();
        CardView card_view=(CardView) findViewById(R.id.calculate_bmi);
        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home1.this,BmiActivity.class);
                startActivity(intent);
            }
        });
    }
    private  void  setupActionBar(){
        Toolbar toolbar_main_activity = (Toolbar) findViewById(R.id.toolbar_main_activity);
        setSupportActionBar(toolbar_main_activity);
        toolbar_main_activity.setNavigationIcon(R.drawable.ic_action_navigation_menu);

        toolbar_main_activity.setNavigationOnClickListener (v->{
            toggleDrawer();
        });

    }

    private void toggleDrawer() {
         DrawerLayout drawer_layout=(DrawerLayout) findViewById(R.id.drawer_layout);
         if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            drawer_layout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
       DrawerLayout drawer_layout=(DrawerLayout) findViewById(R.id.drawer_layout);
         if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            doubleBackToExit();
        }
    }

    private void doubleBackToExit() {
       
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(
                this,"please press  again to exit"
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
}