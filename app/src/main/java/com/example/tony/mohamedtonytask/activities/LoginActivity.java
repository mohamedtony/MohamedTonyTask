package com.example.tony.mohamedtonytask.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tony.mohamedtonytask.R;
import com.example.tony.mohamedtonytask.SharedPref;
import com.example.tony.mohamedtonytask.webServices.RetrofitWebService;
import com.example.tony.mohamedtonytask.webServices.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText userName, mPassword;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
    }

    private void initViews() {
        userName = findViewById(R.id.user_name_login);
        mPassword = findViewById(R.id.userpassord_login);
    }

    //======================== on click for login button ====================
    public void login(View view) {
        if (userName != null && userName.length() != 0 && mPassword != null && mPassword.length() != 0) {
            loginUser(userName.getText().toString(), mPassword.getText().toString());

            showDailog();
        } else {
            Toast.makeText(this, " please input all fields !", Toast.LENGTH_SHORT).show();
        }
    }

    // =============================================== for showing dailog box ===========================================
    //==================================================================================================================
    private void showDailog() {
        pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }


    //=============================================== login web service ================================
    // ==================================================================================================
    private void loginUser(String userName, String mPassword) {

        RetrofitWebService.getService().loginUser(userName, mPassword).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body().getState().equals("1")) {

                    pDialog.dismiss();

                    //============================================ to save logined user  id into shared prefs ==========================
                    SharedPref.getInstance(LoginActivity.this).saveUserID(response.body().getUserdata().get(0).getId());

                    Intent intent=new Intent(LoginActivity.this,AllUsersActivity.class);
                    startActivity(intent);
                    finish();



                } else {
                    Toast.makeText(LoginActivity.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



}
