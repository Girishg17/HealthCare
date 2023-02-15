package com.girish.healthcare.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.girish.healthcare.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class BaseActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base3);

    }
     public Boolean doubleBackToExitPressedOnce = false;
     public Dialog mProgressDialog;

     void showProgressDialog( String text) {
//        TextView tv_progress_text=(TextView) findViewById(R.id.tv_progress_text);

        mProgressDialog = new Dialog(this);

        /*Set the screen content from a layout resource.
        The resource will be inflated, adding all top-level views to the screen.*/
        mProgressDialog.setContentView(R.layout.dialog_progress);
       TextView textView = mProgressDialog.findViewById(R.id.tv_progress_text);
       textView.setText(text);


        //Start the dialog and display it on screen.
        mProgressDialog.show();
    }
     void  hideProgressDialog() {
        mProgressDialog.dismiss();
    }
       String getCurrentUserID() {
        return Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
    }
//
    void doubleBackToExit() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(
                this,
               "press back to exit",
                Toast.LENGTH_SHORT
        ).show();

new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    }, 2000);
    }

    void showErrorSnackBar(String message) {
    Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
    View snackBarView = snackBar.getView();
    ((View) snackBarView).setBackgroundColor(ContextCompat.getColor(this, R.color.snackbar_error_color));
    snackBar.show();
}

}

