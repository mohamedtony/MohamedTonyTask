package com.example.tony.mohamedtonytask.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tony.mohamedtonytask.R;
import com.example.tony.mohamedtonytask.adapters.UsersRecyclerAdapter;
import com.example.tony.mohamedtonytask.webServices.RetrofitWebService;
import com.example.tony.mohamedtonytask.webServices.response.UserData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllUsersActivity extends AppCompatActivity {

    private List<UserData> usersList=new ArrayList<>();
    private UsersRecyclerAdapter adapter;
    private RecyclerView usersRecyclerView;
    private RecyclerView.LayoutManager linearLayout;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);


        initView();
        getAllUsersList();


    }

    //========================================================== getting all users web services ==============================
    private void getAllUsersList() {
        showDailog();
        RetrofitWebService.getService().getAllUsers().enqueue(new Callback<List<UserData>>() {
            @Override
            public void onResponse(Call<List<UserData>> call, Response<List<UserData>> response) {

                pDialog.dismiss();
                adapter = new UsersRecyclerAdapter(AllUsersActivity.this, response.body());
                usersRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<UserData>> call, Throwable t) {

            }
        });
    }

    // =============================================== for showing dailog box ===========================================
    //==================================================================================================================
    private void showDailog() {
        pDialog = new ProgressDialog(AllUsersActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void initView() {
        // Attach the adapter to a ListView
         usersRecyclerView = (RecyclerView) findViewById(R.id.rv_recycler);
         linearLayout=new LinearLayoutManager(this);
         usersRecyclerView.setLayoutManager(linearLayout);
    }
}
