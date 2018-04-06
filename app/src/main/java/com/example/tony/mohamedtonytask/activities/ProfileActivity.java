package com.example.tony.mohamedtonytask.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tony.mohamedtonytask.R;
import com.example.tony.mohamedtonytask.SharedPref;
import com.example.tony.mohamedtonytask.webServices.RetrofitWebService;
import com.example.tony.mohamedtonytask.webServices.response.StatusResponse;
import com.example.tony.mohamedtonytask.webServices.response.UserData;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private ImageView userImage;
    private TextView userFullName, userName, userEmail, mobileNum;
    private String id, image;
    private ProgressDialog pDialog;

    //method to convert the selected image to base64 encoded string
    //=============================================================
    public static String ConvertBitmapToString(Bitmap bitmap) {
        String encodedImage = "";

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        try {
            encodedImage = URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return encodedImage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // id = SharedPref.getInstance(ProfileActivity.this).getUserId();
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey("user_id")) {
            id = getIntent().getExtras().getString("user_id");
        }

        initViews();
        getUserData(id);

    }

    private void initViews() {
        userImage = findViewById(R.id.user_image);
        userFullName = findViewById(R.id.user_full_name);
        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        mobileNum = findViewById(R.id.mobile_number);
    }

    //===================================== click to image view to update=============================
    public void updateUserImage(View view) {

        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data != null && requestCode == 0) {


            if (resultCode == RESULT_OK) {
                Uri targetUri = data.getData();
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                    Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, false);
                    image = ConvertBitmapToString(resizedBitmap);

                    updateImage();

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    //=============================update user Image profile web services =========================
    //=============================================================================================
    private void updateImage() {
        showDailog();

        RetrofitWebService.getService().updateImage(id, image).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                //if(response.body().getState().equals("1")) {
                pDialog.dismiss();
                Toast.makeText(ProfileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                // }
                // Toast.makeText(ProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                //       Log.e(" error ",response.message());
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, " errror " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error", t.getMessage());

            }
        });
    }

    //========================================== getUserData web service ===============================
    //==================================================================================================
    private void getUserData(String id) {
        showDailog();

        RetrofitWebService.getService().getUserData(id).enqueue(new Callback<List<UserData>>() {
            @Override
            public void onResponse(Call<List<UserData>> call, Response<List<UserData>> response) {

                if (response != null) {
                    pDialog.dismiss();
                    loadViews(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<UserData>> call, Throwable t) {
                Log.e("error", t.getMessage());
            }
        });
    }

    //======================================== set data to layout ============================
    private void loadViews(List<UserData> userData) {

        if (userData != null) {

            if (SharedPref.getInstance(this).getUserId().equals(userData.get(0).getId())) {
                userImage.setEnabled(true);
                if (!userData.get(0).getImageProfile().startsWith("profile")) {
                    Picasso.with(ProfileActivity.this).load(userData.get(0).getId()).into(userImage);
                } else {
                    userImage.setImageResource(R.drawable.user_default);
                }
            } else {
                userImage.setEnabled(false);
                if (!userData.get(0).getImageProfile().startsWith("profile")) {
                    Picasso.with(ProfileActivity.this).load(userData.get(0).getId()).into(userImage);
                } else {
                    userImage.setImageResource(R.drawable.users_defal);
                }
            }

            userFullName.setText(userData.get(0).getFullname());
            userName.setText(userData.get(0).getUsername());
            userEmail.setText("Email: " + userData.get(0).getEmail());
            mobileNum.setText("phone: " + userData.get(0).getMobileNumber());
        }

    }


    // =============================================== for showing dailog box ===========================================
    //==================================================================================================================
    private void showDailog() {
        pDialog = new ProgressDialog(ProfileActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }
}
