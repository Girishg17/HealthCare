package com.girish.healthcare.controller;


import com.girish.healthcare.activities.BaseActivity;
import com.girish.healthcare.activities.CreateAccount;
import com.girish.healthcare.models.CreateAccountModel;
import com.google.firebase.auth.FirebaseAuth;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

public class CreateAccountController extends BaseActivity {


    private CreateAccountModel first = new CreateAccountModel();
    private static final String TAG = "controller";
//    String name;
//    String email;
//    String password;
//    public CreateAccountController(String name, String email, String password) {
//        super();
//        this.name=name;
//        this.email=email;
//        this.password=password;
//
//    }

    public Boolean validateForm(String name, String email, String password) {
        if (TextUtils.isEmpty(name)) {
//   showErrorSnackBar(rootview,"Please enter name.");

            return false;
        } else if (TextUtils.isEmpty(email)) {
//    showErrorSnackBar(rootview,"Please enter email.");
            return false;
        } else if (TextUtils.isEmpty(password)) {
//    showErrorSnackBar(rootview,"Please enter password.");
            return false;
        } else {
            return true;
        }
//return true;

    }
     public  Boolean validateFormx(String email,String password){

       if (TextUtils.isEmpty(email)) {
//    showErrorSnackBar("Please enter email.");
    return false;
} else if (TextUtils.isEmpty(password)) {
//    showErrorSnackBar("Please enter password.");
    return false;
} else {
    return true;
}

    }

    public void registerUser(String name, String email, String password,AddUserCallback callback) {
//       AppCompatEditText et_name=(AppCompatEditText)findViewById(R.id.et_name);
//       AppCompatEditText et_email=(AppCompatEditText)findViewById(R.id.et_email);
//       AppCompatEditText et_password=(AppCompatEditText)findViewById(R.id.et_password);
//        String name = et_name.getText().toString().trim();
//        String email = et_email.getText().toString().trim();
//        String password = et_password.getText().toString().trim();

        Log.d(TAG, name);
// Toast.makeText(
//                this,"registered"
//               ,
//                Toast.LENGTH_SHORT
//        ).show();


        first.adduser(email, password, callback);

    }
    public void signinuser(String email, String password, FirebaseAuth mAuth, AddUserCallback callback){
        Log.d(TAG,email);
        first.signUser(email,password,mAuth,callback);

    }



}
