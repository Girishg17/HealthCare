package com.girish.healthcare.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.girish.healthcare.R;
import com.girish.healthcare.controller.MyProfileController;
import com.girish.healthcare.models.FireStoreClass;
import com.girish.healthcare.models.User;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MyProfileActivity extends BaseActivity {
//      private static final int READ_STORAGE_PERMISSION_CODE = 1;
//
//    private static final int PICK_IMAGE_REQUEST_CODE = 2;

//    public Uri mSelectedImageFileUri;
//    private Uri mSelectedImageUri;
//   private User mUserDetails;
//    private String mProfileImageURL = "";
    private MyProfileController controller;

//     public static class Companion {
//
//        public static int getReadStoragePermissionCode() {
//            return READ_STORAGE_PERMISSION_CODE;
//        }
//
//        public static int getPickImageRequestCode() {
//            return PICK_IMAGE_REQUEST_CODE;
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        setupActionBar();
        ImageView nav_user_image = findViewById(R.id.iv_profile_user_image);
        controller=new MyProfileController(this,nav_user_image);
        new FireStoreClass().loadUserProfile(this);
         ImageView nav_user_profile_image = findViewById(R.id.iv_profile_user_image);
        Button update=findViewById(R.id.btn_update);
     nav_user_profile_image.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        controller.showImageChooser(MyProfileActivity.this);
//        if (ContextCompat.checkSelfPermission(MyProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//            == PackageManager.PERMISSION_GRANTED) {
//            showImageChooser();
//        } else {
//            ActivityCompat.requestPermissions(
//                MyProfileActivity.this,
//                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                READ_STORAGE_PERMISSION_CODE
//            );
//        }
    }
});
     update.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        showProgressDialog("Please wait");
        // Here if the image is not selected then update the other details of user.
        if (controller.getMSelectedImageUri() != null) {
            controller.uploadUserImage();
        } else {


            // Call a function to update user details in the database.
            controller.updateUserProfileData();
        }
    }
});



    }

       @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Call the onActivityResult() method of the ImageChooser class
        controller.onActivityResult(requestCode, resultCode, data);
    }



//    private void showImageChooser() {
//    // Check if the permission to read external storage is granted
//    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//        // Request the permission if it is not granted
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_STORAGE_PERMISSION_CODE);
//    } else {
//        // Permission is granted, open the image chooser
//        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
//    }
//}

//@Override
//protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//    super.onActivityResult(requestCode, resultCode, data);
//    if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
//        // Get the selected image URI
//        Uri mSelectedImageUri = data.getData();
//
//         ImageView nav_user_image = findViewById(R.id.iv_profile_user_image);
//            Glide.with(MyProfileActivity.this)
//        .load(mSelectedImageUri) // URL of the image
//        .centerCrop() // Scale type of the image.
//        .placeholder(R.drawable.ic_user_place_holder) // A default place holder
//        .into(nav_user_image);
//
//        // Do something with the selected image URI, such as display it in an ImageView
//        // imageView.setImageURI(mSelectedImageUri);
//    }
//}

//@Override
//public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    if (requestCode == READ_STORAGE_PERMISSION_CODE) {
//        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            // Permission is granted, open the image chooser
//            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
//        } else {
//            // Permission is denied, display a message to the user
//            Toast.makeText(this, "Permission to read external storage is required to select an image.", Toast.LENGTH_LONG).show();
//        }
//    }
//}











    private void setupActionBar() {
        Toolbar toolbar_main_activity = (Toolbar) findViewById(R.id.toolbar_my_profile_activity);
        setSupportActionBar(toolbar_main_activity);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp);
            actionBar.setTitle("My Profile");
        }
        toolbar_main_activity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    public void UserDetails(User loggedInUser) {

        ImageView nav_user_image = findViewById(R.id.iv_profile_user_image);
        TextView tv_username = findViewById(R.id.et_name);
        TextView tv_email = findViewById(R.id.et_email);
        TextView tv_mobile = findViewById(R.id.et_mobile);
        controller.setmUserDetails(loggedInUser);
        Glide.with(MyProfileActivity.this)
                .load(loggedInUser.getImage()) // URL of the image
                .centerCrop() // Scale type of the image.
                .placeholder(R.drawable.ic_user_place_holder) // A default place holder
                .into(nav_user_image); // the view in which the image will be loaded.
        tv_username.setText(loggedInUser.getName());
        tv_email.setText(loggedInUser.getEmail());
        if (loggedInUser.getMobile() != 0) {
            tv_mobile.setText(String.valueOf(loggedInUser.getMobile()));

        }
    }

}