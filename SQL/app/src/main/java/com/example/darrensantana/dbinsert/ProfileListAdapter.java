package com.example.darrensantana.dbinsert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProfileListAdapter extends ArrayAdapter<Profile> {

    private List<Profile> profiles = new ArrayList<Profile>();
    Context mContext;

    public ProfileListAdapter(Context context, int resource, List<Profile> profile) {
        super(context, resource, profile);
        profiles = profile;
        mContext = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView == null){

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.profile_list,parent,false);


        }
        Profile profileInfo = profiles.get(position);
        TextView firstname = (TextView)convertView.findViewById(R.id.displayName);
        TextView lastname = (TextView)convertView.findViewById(R.id.displayLastName);
        TextView age = (TextView)convertView.findViewById(R.id.displayAge);
        TextView username = (TextView)convertView.findViewById(R.id.accountUsername);
        TextView password = (TextView)convertView.findViewById(R.id.accountPassword);
        ImageView profileImage = (ImageView)convertView.findViewById(R.id.ivProfileImage);
        profileImage.setImageBitmap(profileInfo.getProfileImage());
        username.setText(profileInfo.getUsername());
        password.setText(profileInfo.getPassword());
        firstname.setText(profileInfo.getName());
        lastname.setText(profileInfo.getLastname());
        age.setText(profileInfo.getAge());

        return convertView;
    }
}