package com.girish.healthcare.models;



import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String id;
    private String name;
    private String email;
   private String image;
   private long mobile;
    private String fcmToken;
    public User(){

    }

    public User(String id,String name, String email, String fcmToken,String image,long mobile) {
        this.id = id;
       this.name = name;
        this.email = email;
       this.image = image;
       this.mobile = mobile;
        this.fcmToken = fcmToken;
    }

    protected User(Parcel in) {
        id = in.readString();
       name = in.readString();
        email = in.readString();
       image = in.readString();
       mobile = in.readLong();
        fcmToken = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
       dest.writeString(name);
        dest.writeString(email);
       dest.writeString(image);
        dest.writeLong(mobile);
        dest.writeString(fcmToken);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }

    public long getMobile() {
        return mobile;
    }

    public String getFcmToken() {
        return fcmToken;
    }
}

