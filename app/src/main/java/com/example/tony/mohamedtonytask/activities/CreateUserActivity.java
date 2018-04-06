package com.example.tony.mohamedtonytask.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.tony.mohamedtonytask.R;
import com.example.tony.mohamedtonytask.webServices.RetrofitWebService;
import com.example.tony.mohamedtonytask.webServices.request.CreateUserModel;
import com.example.tony.mohamedtonytask.webServices.response.StatusResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUserActivity extends AppCompatActivity {
    private EditText firstName, midName, lastName, userName, mPassword, confirmPassword, mEmail, mobilePhone;
    private int Gender = -1;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        initViews();

    }

    //================================ on click button submit =======================
    public void submit(View view) {
        CreateUserModel createUserModel = new CreateUserModel();
        if (firstName != null && firstName.length() != 0 &&
                midName != null && midName.length() != 0 &&
                lastName != null && lastName.length() != 0 &&
                userName != null && userName.length() != 0 &&
                mPassword != null && mPassword.length() != 0 &&
                confirmPassword != null && confirmPassword.length() != 0 &&
                mEmail != null && mEmail.length() != 0 &&
                mobilePhone != null && mobilePhone.length() != 0) {


            showDailog();

            createUserModel.setFirstname(firstName.getText().toString());
            createUserModel.setMidname(midName.getText().toString());
            createUserModel.setLastname(lastName.getText().toString());
            createUserModel.setUsername(userName.getText().toString());
            createUserModel.setPassword(mPassword.getText().toString());
            createUserModel.setConfirm_password(confirmPassword.getText().toString());
            createUserModel.setEmail(mEmail.getText().toString());
            createUserModel.setMobile_number(mobilePhone.getText().toString());
            createUserModel.setFk_gender_id(Gender);

            createUser(createUserModel);
        } else {
            Toast.makeText(this, " Please Input All Fields ! ", Toast.LENGTH_SHORT).show();
        }


    }
    // =============================================== for showing dailog box ===========================================
    //==================================================================================================================
    private void showDailog() {
        pDialog = new ProgressDialog(CreateUserActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }


    //========================> create user web service <====================
    private void createUser(CreateUserModel createUserModel) {

        //getUserBody();


        //==========================  add user api ========================================
        RetrofitWebService.getService().createUser(createUserModel).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {

                if (response.body().getState().equals("1")) {
                    pDialog.dismiss();
                    Toast.makeText(CreateUserActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateUserActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(CreateUserActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Toast.makeText(CreateUserActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    //========================= get string form edit text and put them in the model =======================================
    private CreateUserModel getUserBody() {

        CreateUserModel createUserModel = new CreateUserModel();
        if (firstName != null && midName != null && lastName != null && userName != null && mPassword != null && confirmPassword != null && mEmail != null && mobilePhone != null) {
            createUserModel.setFirstname(firstName.getText().toString());
            createUserModel.setMidname(midName.getText().toString());
            createUserModel.setLastname(lastName.getText().toString());
            createUserModel.setUsername(userName.getText().toString());
            createUserModel.setPassword(mPassword.getText().toString());
            createUserModel.setConfirm_password(confirmPassword.getText().toString());
            createUserModel.setEmail(mEmail.getText().toString());
            createUserModel.setMobile_number(mobilePhone.getText().toString());
            createUserModel.setFk_gender_id(Gender);
        } else {
            Toast.makeText(this, " Please Input All Fields ! ", Toast.LENGTH_SHORT).show();
        }

        return createUserModel;
    }

    private void initViews() {

        firstName = findViewById(R.id.first_name);
        midName = findViewById(R.id.mid_name);
        lastName = findViewById(R.id.last_name);
        userName = findViewById(R.id.user_name);
        mPassword = findViewById(R.id.user_password);
        confirmPassword = findViewById(R.id.confirm_password);
        mEmail = findViewById(R.id.user_email);
        mobilePhone = findViewById(R.id.phone_number);
    }

    //=============================== For Radio Buttons Clicks===================================
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_male:
                if (checked)
                    // Pirates are the best
                    Gender = 1;
                break;
            case R.id.radio_female:
                if (checked)
                    // Ninjas rule
                    Gender = 0;
                break;
        }
    }
}
