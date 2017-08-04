package com.example.darrensantana.dbinsert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends FragmentActivity implements LoginFragment.LoginFragmentEventListener{
    FragmentManager manager;
    LoginFragment loginFragment;
    RequestQueue requestQueue;
    StringRequest request;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadLoginFragment();
    }

    private void loadLoginFragment() {
        manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        loginFragment = new LoginFragment();
        ft.replace(R.id.login_layout, loginFragment);
        ft.commit();
    }

    public void login(final String username, final String password){
        requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, "http://172.16.5.30/php/login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.names().get(0).equals("success")) {
                            Toast.makeText(getApplicationContext(), "Login Succesful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                            user = jsonObject.getString("success");
                            SharedPreferences myprefs= getSharedPreferences("user", MODE_WORLD_READABLE);
                            myprefs.edit().putString("session_id", user).commit();

                        } else {
                            Toast.makeText(getApplicationContext(), "ERROR" + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                        }

                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("username", username);
                hashMap.put("password", password);

                return hashMap;
            }
        };
        requestQueue.add(request);
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
