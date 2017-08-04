package com.example.darrensantana.dbinsert;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import static android.R.attr.data;
import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class InsertFragment extends Fragment {
    private InsertFragmentEventListener mListener;
    Button btnInsert;
    EditText name, lastName, username, password;
    String imageAction;
    ImageView ivThumbnail;
    private static final int RESULT_LOAD_IMAGE = 100;
    private static final int CAMERA_REQUEST = 1888;
    Bitmap image;


    public InsertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert, container, false);

        btnInsert = (Button) view.findViewById(R.id.btnInsert);
        ivThumbnail = (ImageView)view.findViewById(R.id.ivThumbnail);

        name = (EditText) view.findViewById(R.id.etName);
        lastName = (EditText) view.findViewById(R.id.etLastName);
        username = (EditText) view.findViewById(R.id.etUsername);
        password = (EditText) view.findViewById(R.id.etPassword);

//        preguntar por permiso de la camara al comensar el fragmento
//        ActivityCompat.requestPermissions(getActivity(),
//                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

        btnInsert.setOnClickListener(insert);

        ivThumbnail.setOnClickListener(selectImageIntent);

        return view;
    }

    private View.OnClickListener selectImageIntent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selectImage();
        }
    };


// aqui es donde cogemos el resultado de el intento de la camara or el gallery con la modificasion de un switch ya que estamos escogiendo entre gallery y camara.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAMERA_REQUEST:
                    image = (Bitmap) data.getExtras().get("data");
                    ivThumbnail.setImageBitmap(image);
                    break;

                case RESULT_LOAD_IMAGE:
                    if (ActivityCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {

                        Uri selectedImage = data.getData();
                        String[] filePathColumn = { MediaStore.Images.Media.DATA };

                        Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String picturePath = cursor.getString(columnIndex);
                        cursor.close();

                        ivThumbnail.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                        image = BitmapFactory.decodeFile(picturePath);


                    } else {
                        // permission denied, boo! Disable the
                        // functionality that depends on this permission.
                        Toast.makeText(getActivity(), "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                    }
                    break;

            }
        }
    }

    //creamos nuestro alert dialog para poder escoger opciones en 1 solo botton.
    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(getActivity());
                if (items[item].equals("Take Photo")) {
                    imageAction="camera";
                    if(result)
                        imageIntent(imageAction);
                } else if (items[item].equals("Choose from Library")) {
                    imageAction="gallery";
                    if(result)
                        imageIntent(imageAction);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    //chequiamos los permisos de poder leer y escrivir el external storage.
    public static class Utility {
        public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public static boolean checkPermission(final Context context)
        {
            int currentAPIVersion = Build.VERSION.SDK_INT;
            if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
            {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                        alertBuilder.setCancelable(true);
                        alertBuilder.setTitle("Permission necessary");
                        alertBuilder.setMessage("External storage permission is necessary");
                        alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                            }
                        });
                        AlertDialog alert = alertBuilder.create();
                        alert.show();
                    } else {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
    }

    private View.OnClickListener insert = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            if (TextUtils.isEmpty(name.getText().toString())) {
                name.setHint("Please enter username");
                name.setHintTextColor(Color.RED);
            }else if(TextUtils.isEmpty(lastName.getText().toString())){
                lastName.setHint("Please enter Password");
                lastName.setHintTextColor(Color.RED);
            }else if(TextUtils.isEmpty(username.getText().toString())){
                username.setHint("Please enter Password");
                username.setHintTextColor(Color.RED);
            }else if(TextUtils.isEmpty(password.getText().toString())){
                password.setHint("Please enter Password");
                password.setHintTextColor(Color.RED);
            }else if(image == null){
                Toast.makeText(getActivity(), "You must include a profile image", Toast.LENGTH_SHORT).show();
            }else {
                mListener.insertProfile(name.getText().toString(), lastName.getText().toString(), username.getText().toString(), password.getText().toString(), image);
                name.setText("");
                lastName.setText("");
                username.setText("");
                password.setText("");
                ivThumbnail.setImageBitmap(null);
            }
        }
    };

    public void imageIntent(String action){
        switch (imageAction){

            case "camera":
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                break;

            case "gallery":
                Intent galleryIntent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (InsertFragmentEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement InsertFragmentEventListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface InsertFragmentEventListener {
        void insertProfile(String name, String lastName, String username, String password, Bitmap image);
    }

}
