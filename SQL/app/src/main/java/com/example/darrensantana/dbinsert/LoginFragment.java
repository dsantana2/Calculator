package com.example.darrensantana.dbinsert;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private LoginFragmentEventListener mListener;
    EditText mUsername, mPassword;
    Button btnLogin;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        btnLogin = (Button)view.findViewById(R.id.btnLogin);

        mUsername = (EditText)view.findViewById(R.id.etUsername);
        mPassword = (EditText)view.findViewById(R.id.etPassword);

            btnLogin.setOnClickListener(Login);

        return view;
    }

    private View.OnClickListener Login = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (TextUtils.isEmpty(mUsername.getText().toString())) {
                mUsername.setHint("Please enter username");
                mUsername.setHintTextColor(Color.RED);
            }else if(TextUtils.isEmpty(mPassword.getText().toString())){
                mPassword.setHint("Please enter Password");
                mPassword.setHintTextColor(Color.RED);
            }else {
                mListener.login(mUsername.getText().toString(), mPassword.getText().toString());
            }

        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (LoginFragmentEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement LoginFragmentEventListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface LoginFragmentEventListener {
        void login(String username, String password);
    }
}
