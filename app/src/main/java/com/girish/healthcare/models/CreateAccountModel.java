package com.girish.healthcare.models;

import com.girish.healthcare.activities.BaseActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.girish.healthcare.R;
import com.girish.healthcare.activities.CreateAccount;
import com.girish.healthcare.activities.MainActivity;
import com.girish.healthcare.controller.AddUserCallback;
import com.girish.healthcare.controller.CreateAccountController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccountModel extends BaseActivity {

    private static final String TAG = "MyApp";
    Boolean f = true;
    private  FireStoreClass FireStoraget=new FireStoreClass();
//     private FirebaseAuth mAuth;


    public Boolean adduser(String name, String email, String password, AddUserCallback callback) {
//         showProgressDialog("please wait");

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                       //hideProgressDialog();

                        FirebaseUser firebaseUser = task.getResult().getUser();
                        assert firebaseUser != null;
                        String registeredEmail = firebaseUser.getEmail();
                        User user = new User(firebaseUser.getUid(),name,registeredEmail,""
                        );
                        FireStoraget.registerUser(user);
                    //    FirebaseAuth.getInstance().signOut();
                            FirebaseAuth.getInstance().signOut();


                         callback.onSuccess();



//                User user = new User(firebaseUser.getUid(), name, registeredEmail);
//                FirestoreClass.INSTANCE.registerUser(this, user);
                    } else {

                        callback.onFailure("failed");
//                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });
        return f;
    }
    public void signUser(String email, String password, FirebaseAuth mAuth, AddUserCallback callback){

                    mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener( task -> {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                           FirebaseUser user = mAuth.getCurrentUser();
                            callback.onSuccess();
//                                Intent intent = new Intent(MainActivity.this, Home1.class);
//                                startActivity(intent);

                        } else {


//                                Toast.makeText(MainActivity.this, "Authentication failed.",
//                                        Toast.LENGTH_SHORT).show();
                            callback.onFailure("failed");

                        }
                    });
    }


}
