package com.girish.healthcare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.girish.healthcare.R;
import com.girish.healthcare.activities.CreateAccount;
import com.girish.healthcare.activities.Home1;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
     MaterialButton b1;
     TextView b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         b1= (MaterialButton) findViewById(R.id.login);
         b2=(TextView)findViewById(R.id.create);
                getWindow().setFlags(
    WindowManager.LayoutParams.FLAG_FULLSCREEN,
    WindowManager.LayoutParams.FLAG_FULLSCREEN
);
         b2.setOnClickListener(v->{
                Intent intent=new Intent(this, CreateAccount.class);
               startActivity(intent);
         });

       b1.setOnClickListener(v->{
//         Intent intent=new Intent(this,Home1.class);
//      startActivity(intent);
           onclick();
        });
    }
    private void onclick(){
                 Intent intent=new Intent(this, Home1.class);
      startActivity(intent);

    }
}