package com.girish.healthcare.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.girish.healthcare.R;
import com.girish.healthcare.controller.AddUserCallback;
import com.girish.healthcare.controller.CreateAccountController;
import com.girish.healthcare.models.CreateAccountModel;

public class CreateAccount extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        CreateAccountController sec = new CreateAccountController();
        CreateAccountModel third = new CreateAccountModel();
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setupActionBar();

        Button btn_signup = (Button) findViewById(R.id.btn_sign_up);
        btn_signup.setOnClickListener(v -> {
            showProgressDialog("please wait");
//         Intent intent=new Intent(this,Home1.class);
//      startActivity(intent);
//           registerUser();
            AppCompatEditText et_name = (AppCompatEditText) findViewById(R.id.et_name);
            AppCompatEditText et_email = (AppCompatEditText) findViewById(R.id.et_email);
            AppCompatEditText et_password = (AppCompatEditText) findViewById(R.id.et_password);
            String name = et_name.getText().toString().trim();
            String email = et_email.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            View rootView = findViewById(android.R.id.content);
            boolean validate = sec.validateForm(name, email, password);


            if (validate) {

                sec.registerUser(name, email, password,
                        new AddUserCallback() {
                            @Override
                            public void onSuccess() {
                                hideProgressDialog();
                                Toast.makeText(
                                        CreateAccount.this, name + "registered with with email"
                                        ,
                                        Toast.LENGTH_SHORT
                                ).show();
                                finish();
                            }

                            @Override
                            public void onFailure(String message) {
                                                      hideProgressDialog();
                                Toast.makeText(
                                        CreateAccount.this, name + "error"
                                        ,
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }
                );

            } else {
                hideProgressDialog();
                showErrorSnackBar("Please enter all details.");
            }
        });


    }


    private void setupActionBar() {
        Toolbar toolbar_sign_up_activity = findViewById(R.id.toolbar_sign_up_activity);
        setSupportActionBar(toolbar_sign_up_activity);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp);
        }
        toolbar_sign_up_activity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
//    protected Boolean validateForm(String name,String email,String password){
//        if (TextUtils.isEmpty(name)) {
//    showErrorSnackBar("Please enter name.");
//    return false;
//} else if (TextUtils.isEmpty(email)) {
//    showErrorSnackBar("Please enter email.");
//    return false;
//} else if (TextUtils.isEmpty(password)) {
//    showErrorSnackBar("Please enter password.");
//    return false;
//} else {
//    return true;
//}
//
//    }
//   private  void registerUser(){
//       AppCompatEditText et_name=(AppCompatEditText)findViewById(R.id.et_name);
//           AppCompatEditText et_email=(AppCompatEditText)findViewById(R.id.et_email);
//               AppCompatEditText et_password=(AppCompatEditText)findViewById(R.id.et_password);
// String name = et_name.getText().toString().trim();
//String email = et_email.getText().toString().trim();
//String password = et_password.getText().toString().trim();
//if(validateForm(name,email,password)){
//// Toast.makeText(
////                this,"registered"
////               ,
////                Toast.LENGTH_SHORT
////        ).show();
//
//    showProgressDialog("Please Wait");
//    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
//    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//        @Override
//        public void onComplete(@NonNull Task<AuthResult> task) {
//            if (task.isSuccessful()) {
//                hideProgressDialog();
//                FirebaseUser firebaseUser = task.getResult().getUser();
//                assert firebaseUser != null;
//                String registeredEmail = firebaseUser.getEmail();
//                 Toast.makeText(
//               CreateAccount.this,name+"registered with "+firebaseUser+"with email"+registeredEmail
//               ,
//                Toast.LENGTH_SHORT
//        ).show();
//    FirebaseAuth.getInstance().signOut();
//    finish();
////                User user = new User(firebaseUser.getUid(), name, registeredEmail);
////                FirestoreClass.INSTANCE.registerUser(this, user);
//            } else {
//                Toast.makeText(CreateAccount.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    });
//
//}
//
//   }
}