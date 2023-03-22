package com.girish.healthcare.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.girish.healthcare.R;
import com.girish.healthcare.activities.CreateAccount;
import com.girish.healthcare.activities.Home1;
import com.girish.healthcare.controller.AddUserCallback;
import com.girish.healthcare.controller.CreateAccountController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends BaseActivity {
    MaterialButton b1;
    TextView b2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        b1 = (MaterialButton) findViewById(R.id.login);
        b2 = (TextView) findViewById(R.id.create);
        CreateAccountController sec = new CreateAccountController();
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        b2.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateAccount.class);
            startActivity(intent);
        });

        b1.setOnClickListener(v -> {
            EditText et_email = (EditText) findViewById(R.id.username);
            EditText et_password = (EditText) findViewById(R.id.password);
            String email = et_email.getText().toString();
            String password = et_password.getText().toString();
//            Toast.makeText(MainActivity.this, email + " " + password + " this",
//                    Toast.LENGTH_SHORT).show();

            boolean validate = sec.validateFormx(email, password);
            if (validate) {
                Toast.makeText(MainActivity.this, " validated " + email,
                        Toast.LENGTH_SHORT).show();
                showProgressDialog("Please Wait");

                sec.signinuser(email, password, mAuth, new AddUserCallback() {
                    @Override
                    public void onSuccess() {
                        hideProgressDialog();
                        Intent intent = new Intent(MainActivity.this, Home1.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(String message) {
                        hideProgressDialog();
                        Toast.makeText(MainActivity.this, "username and password didn't match ",
                                Toast.LENGTH_SHORT).show();

                    }
                });

            } else {
                showErrorSnackBar("Please enter all details.");
            }

        });
    }

//    private void SignInRegisterUser() {
//
//
//        EditText et_email = (EditText) findViewById(R.id.username);
//        EditText et_password = (EditText) findViewById(R.id.password);
//        String email = et_email.getText().toString();
//        String password = et_password.getText().toString();
//        Toast.makeText(MainActivity.this, email + " " + password + " this",
//                Toast.LENGTH_SHORT).show();
//
//        if (validateForm(email, password)) {
//            showProgressDialog("Please Wait");
//
//            mAuth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this, task -> {
//                        hideProgressDialog();
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d("Sign In", "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            Intent intent = new Intent(MainActivity.this, Home1.class);
//                            startActivity(intent);
//
//                        } else {
//
//                            Log.w("Sign In", "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(MainActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//
//                        }
//                    });
//
//        }
//
//
//    }

//    protected Boolean validateForm(String email, String password) {
//        View rootView = findViewById(android.R.id.content);
//        if (TextUtils.isEmpty(email)) {
//            showErrorSnackBar("Please enter email.");
//            return false;
//        } else if (TextUtils.isEmpty(password)) {
//            showErrorSnackBar("Please enter password.");
//            return false;
//        } else {
//            return true;
//        }
//
//    }
}