package com.example.darrensantana.dbinsert;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by darrensantana on 5/19/17.
 */

public class Profile implements Parcelable {

    private String name;
    private String lastname;
    private String date;
    private String username;
    private String password;
    private Bitmap profileImage;

        public Profile(String name, String lastname, String username, String password, String date, Bitmap profileImage){
            super();
            this.name = name;
            this.lastname = lastname;
            this.username = username;
            this.password = password;
            this.date = date;
            this.profileImage = profileImage;
        }

    protected Profile(Parcel in) {
        name = in.readString();
        lastname = in.readString();
        date = in.readString();
        username = in.readString();
        password = in.readString();
        profileImage = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAge() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAge(String age) {
        this.date = age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Bitmap getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Bitmap profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(lastname);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(date);
        dest.writeString(String.valueOf(profileImage));

    }
}



