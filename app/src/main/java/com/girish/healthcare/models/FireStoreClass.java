package com.girish.healthcare.models;

import com.girish.healthcare.activities.CreateAccount;
//import com.google.android.gms.common.internal.Constants;
import com.girish.healthcare.activities.Home1;
import com.girish.healthcare.activities.MainActivity;
import com.girish.healthcare.controller.AddUserCallback;
import com.girish.healthcare.utils.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.SetOptions;


// TODO (Step 3: Create a class where we will add the operation performed for the firestore database.)
// START

/**
 * A custom class where we will add the operation performed for the firestore database.
 */
public class FireStoreClass {

    // Create a instance of Firebase Firestore
    private FirebaseFirestore mFireStore = FirebaseFirestore.getInstance();

    // TODO (Step 5: Create a function to make an entry of the registered user in the firestore database.)

    /**
     * A function to make an entry of the registered user in the firestore database.
     */
    public void registerUser(User userInfo) {

        CreateAccount activity = new CreateAccount();
        ;
        mFireStore.collection(Constants.USERS)
                // Document ID for users fields. Here the document it is the User ID.
                .document(getCurrentUserID())
                // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge
                .set(userInfo, SetOptions.merge())
                .addOnSuccessListener(aVoid -> {

                    // Here call a function of base activity for transferring the result to it.
                    activity.userRegisteredSuccess();
                })
                .addOnFailureListener(e -> Log.e(
                        activity.getClass().getSimpleName(),
                        "Error writing document",
                        e
                ));
    }

    public void signInUser(Activity activity) {

        mFireStore.collection(Constants.USERS)
                .document(getCurrentUserID())
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    Log.e(activity.getClass().getSimpleName(), documentSnapshot.toString());

                    // TODO (STEP 3: Pass the result to base activity.)
                    // START
                    User loggedInUser = documentSnapshot.toObject(User.class);

                    // Here call a function of base activity for transferring the result to it.
                   if(activity instanceof MainActivity){
                       ((MainActivity)activity).signInSuccess(loggedInUser);
                   }
                   else if(activity instanceof Home1){
                       ((Home1) activity).updateNavigationUserDetails(loggedInUser);
                   }
//                   else if (activity instanceof MyProfileActivity) {
//                        ((MyProfileActivity) activity).setUserDataInUI(loggedInUser);
//                    }

                    // END
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(activity.getClass().getSimpleName(),
                                "Error while getting loggedIn user details", e);
                    }
                });
    }


    // TODO (Step 6: Create a function for getting the user id of current logged user.)
    // START

    /**
     * A function for getting the user id of current logged user.
     * //
     */
//    public String getCurrentUserID() {
//        FirebaseUser currentuser=FirebaseAuth.getInstance().getCurrentUser();
//
//        return FirebaseAuth.getInstance().getCurrentUser().getUid();
//    }
    public String getCurrentUserID() {
        // An Instance of currentUser using FirebaseAuth
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        // A variable to assign the currentUserId if it is not null or else it will be blank.
        String currentUserID = "";
        if (currentUser != null) {
            currentUserID = currentUser.getUid();
        }

        return currentUserID;
    }

}
