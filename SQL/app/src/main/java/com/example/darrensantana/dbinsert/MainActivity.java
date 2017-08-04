package com.example.darrensantana.dbinsert;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


public class MainActivity extends FragmentActivity implements displayProfileFragment.displayProfileFragmentEventListener, InsertFragment.InsertFragmentEventListener{
    RequestQueue requestQueue;
    Button insertView, displayProfileView, btnLogout;
    TextView accountName;
    String insertUrl = "http://172.16.5.30/php/insertProfile.php";
    String firstname, lastname, username, password, date, profileImage;
    private List<Profile> personProfile = new ArrayList<Profile>();
    FragmentManager manager;
    private InsertFragment insertFragment;
    private displayProfileFragment displayFragment;
    String account;
    int horizontalMaxValue, horizontalProgressValue = 0;
    ProgressBar horizontalProgressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertView = (Button)findViewById(R.id.btnInsertView);
        displayProfileView = (Button)findViewById(R.id.btnDbDisplay);
        btnLogout = (Button)findViewById(R.id.btnLogout);
        horizontalProgressBar = (ProgressBar) findViewById(R.id.horizontalProgressBar);
        accountName =(TextView)findViewById(R.id.txtAccount);
        horizontalMaxValue = horizontalProgressBar.getMax();
        horizontalProgressValue = horizontalProgressBar.getProgress();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        SharedPreferences myprefs= getSharedPreferences("user", MODE_WORLD_READABLE);
        account = myprefs.getString("session_id", null);
        insertView.setOnClickListener(insertProfileView);
        displayProfileView.setOnClickListener(profileView);
        btnLogout.setOnClickListener(logout);
        accountName.setText("Welcome"+" "+account);
        loadInsertFragment();

    }

    private View.OnClickListener insertProfileView = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            loadInsertFragment();
        }
    };

    private View.OnClickListener profileView = new View.OnClickListener() {
        @Override
        public void onClick(final View v)
        {
            print();

        }
    };

    private View.OnClickListener logout = new View.OnClickListener() {
        @Override
        public void onClick(final View v)
        {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
    };

    private void loadInsertFragment() {
        manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        insertFragment = new InsertFragment();
        ft.setCustomAnimations(R.anim.enter_from_right,
                R.anim.exit_to_left);
        ft.replace(R.id.fragLayout, insertFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void loadDisplayProfileFragment(List<Profile> profile) {
        manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Bundle args = new Bundle();
        args.putParcelableArrayList("arraylist", (ArrayList<? extends Parcelable>) profile);
        displayFragment = new displayProfileFragment();
        displayFragment.setArguments(args);
        ft.setCustomAnimations(R.anim.enter_from_right,
                R.anim.exit_to_left);
        ft.replace(R.id.fragLayout, displayFragment);
        ft.addToBackStack(null);
        ft.commit();

    }

    //aqui mandamos el profile al php para que luego el php los inserte en la base de datos
    public void insertProfile(final String name, final String lastName, final String username, final String password, final Bitmap image){

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("name", name);
                parameters.put("lastname", lastName);
                parameters.put("username", username);
                parameters.put("password", password);
                parameters.put("image", getStringImage(image));
                return parameters;
            }
        };
        requestQueue.add(request);
    }

    //aqui es que vamos a recuperar todos los profiles del php.
    public void print(){
        StringRequest request2 = new StringRequest(Request.Method.POST, "http://172.16.5.30/php/list.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray dbProfiles = new JSONObject(response.toString()).getJSONArray("Profile_List");
                    for (int i = 0; i < dbProfiles.length(); i++) {

                        JSONObject profile = dbProfiles.getJSONObject(i);
                        firstname = profile.getString("firstname");
                        lastname = profile.getString("lastname");
                        username = profile.getString("username");
                        password = profile.getString("password");
                        date = profile.getString("Date");
                        profileImage = profile.getString("profileImage");
                        byte[] decodedString = Base64.decode(profileImage, Base64.DEFAULT);
                        Bitmap decodedbyte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        personProfile.add(new Profile(firstname, lastname, username, password, getDate(date), decodedbyte));
                        loadDisplayProfileFragment(personProfile);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request2);
    }

    public void practice(){
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
    });
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private String getDate(String OurDate)
    {
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
            Date value = formatter.parse(OurDate);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            dateFormatter.setTimeZone(TimeZone.getDefault());
            OurDate = dateFormatter.format(value);
        }
        catch (Exception e)
        {

        }
        return OurDate;
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}
