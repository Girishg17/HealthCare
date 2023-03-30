package com.girish.healthcare.controller;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.security.keystore.BackendBusyException;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;

import com.bumptech.glide.Glide;
import com.girish.healthcare.activities.BaseActivity;
import com.girish.healthcare.activities.MyProfileActivity;
import com.girish.healthcare.R;
import com.girish.healthcare.models.FireStoreClass;
import com.girish.healthcare.models.User;
import com.girish.healthcare.utils.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import android.Manifest;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Objects;


public class MyProfileController extends BaseActivity {


    private static final int READ_STORAGE_PERMISSION_CODE = 1;
    private static final int PICK_IMAGE_REQUEST_CODE = 2;

    public Uri getMSelectedImageUri() {
        return mSelectedImageUri;
    }

    private Uri mSelectedImageUri;
    private MyProfileActivity context;
    private ImageView img_view;

    public User getmUserDetails() {
        return mUserDetails;
    }

    public void setmUserDetails(User mUserDetails) {
        this.mUserDetails = mUserDetails;
    }

    public User mUserDetails;

    public String getmProfileImageURL() {
        return mProfileImageURL;
    }

    public void setmProfileImageURL(String mProfileImageURL) {
        this.mProfileImageURL = mProfileImageURL;
    }

    private String mProfileImageURL = "";

    public MyProfileController(MyProfileActivity myProfileActivity, ImageView nav_user_image) {
        super();
        img_view = nav_user_image;
        context = myProfileActivity;

    }

    public void updateUserProfileData() {


        HashMap<String, Object> userHashMap = new HashMap<>();
        TextView et_name = ((Activity) context).findViewById(R.id.et_name);

        TextView et_mobile =  ((Activity) context).findViewById(R.id.et_mobile);



        if (!TextUtils.isEmpty(mProfileImageURL) && !mProfileImageURL.equals(mUserDetails.getImage())) {
            userHashMap.put(Constants.IMAGE, mProfileImageURL);

        }

        if (!et_name.getText().toString().equals(mUserDetails.getName())) {
            userHashMap.put(Constants.NAME, et_name.getText().toString());

        }

        if (!et_mobile.getText().toString().equals(String.valueOf(mUserDetails.getMobile()))) {
            userHashMap.put(Constants.MOBILE, Long.parseLong(et_mobile.getText().toString()));

        }




            // Update the data in the database.
            new FireStoreClass().updateUserProfileData(context, userHashMap);

    }


    // Call this method to open the image chooser
    public void showImageChooser(Activity activity) {
        // Check if the permission to read external storage is granted
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Request the permission if it is not granted
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_STORAGE_PERMISSION_CODE);
        } else {
            // Permission is granted, open the image chooser
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activity.startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
        }
    }

    // This method is called when the user has selected an image from the image chooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            // Get the selected image URI
            mSelectedImageUri = data.getData();

            Glide.with(context)
                    .load(mSelectedImageUri) // URL of the image
                    .centerCrop() // Scale type of the image.
                    .placeholder(R.drawable.ic_user_place_holder) // A default place holder
                    .into(img_view);

            // Do something with the selected image URI, such as display it in an ImageView
            // imageView.setImageURI(mSelectedImageUri);
        }
    }

    // This method is called when the user has granted or denied the permission to read external storage
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted, open the image chooser
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
            } else {
                // Permission is denied, display a message to the user
                Toast.makeText(this, "Permission to read external storage is required to select an image.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void uploadUserImage() {


        if (mSelectedImageUri != null) {
            //getting the storage reference
            StorageReference sRef = FirebaseStorage.getInstance().getReference().child(
                    "USER_IMAGE" + System.currentTimeMillis() + "." + getFileExtension(context,
                            mSelectedImageUri
                    ));

            //adding the file to reference
            sRef.putFile(mSelectedImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // The image upload is success


                            // Get the downloadable url from the task snapshot
                            Objects.requireNonNull(taskSnapshot.getMetadata().getReference()).getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
//                                            Log.e("Downloadable Image URL", uri.toString());

                                            // assign the image url to the variable.
//                                            mProfileImageURL = uri.toString();
                                            setmProfileImageURL(uri.toString());
                                            updateUserProfileData();
                                           // context.hideProgressDialog();
                                            // Call a function to update user details in the database.
                                            //updateUserProfileData();
                                        }
                                    });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {


                            context.hideProgressDialog();
                            Toast.makeText(context, "could not able to upload image", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }

    public String getFileExtension(Context context, Uri uri) {
        ContentResolver cR = context.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}


// Companion object equivalent in Java as a static inner class





