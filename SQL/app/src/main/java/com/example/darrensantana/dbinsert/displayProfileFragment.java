package com.example.darrensantana.dbinsert;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.hardware.display.VirtualDisplay;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.keepScreenOn;


/**
 * A simple {@link Fragment} subclass.
 */
public class displayProfileFragment extends Fragment {
    private displayProfileFragmentEventListener mListener;
    ListView profileList;
    public static final String KEY = "arraylist";
    ArrayList<Profile> myProfile;


    public displayProfileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display_profile, container, false);
        profileList = (ListView)view.findViewById(R.id.profileList);
        myProfile = getArguments().getParcelableArrayList(KEY);
        populate_ListView();

        return view;
    }


    private void populate_ListView() {
        ArrayAdapter<Profile> adapter = new ProfileListAdapter(getActivity(), R.id.profileList, myProfile);
        profileList.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (displayProfileFragmentEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement displayProfileFragmentEventListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface displayProfileFragmentEventListener {

    }

}
