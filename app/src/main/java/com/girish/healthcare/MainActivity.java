package com.girish.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
     MaterialButton b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         b1= (MaterialButton) findViewById(R.id.login);
       b1.setOnClickListener(v->{
//         Intent intent=new Intent(this,Home1.class);
//      startActivity(intent);
           onclick();
        });
    }
    private void onclick(){
                 Intent intent=new Intent(this,Home1.class);
      startActivity(intent);

    }
}