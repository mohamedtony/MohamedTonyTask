package com.example.tony.mohamedtonytask.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tony.mohamedtonytask.R;
import com.example.tony.mohamedtonytask.activities.AllUsersActivity;
import com.example.tony.mohamedtonytask.activities.ProfileActivity;
import com.example.tony.mohamedtonytask.webServices.response.UserData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersRecyclerAdapter  extends RecyclerView.Adapter<UsersRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<UserData> usersList = new ArrayList<>();

    public UsersRecyclerAdapter(Context mContext, List<UserData> usersList) {

        this.usersList = usersList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_user_item, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(mContext.getApplicationContext(),Company_Detail.class);
                mContext.startActivity(intent);*/
            }
        });

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.userName.setText(usersList.get(position).getFullname());
        holder.userEmail.setText(String.valueOf(usersList.get(position).getEmail()));
        if(!usersList.get(position).getImageProfile().startsWith("profile")){
            Picasso.with(mContext).load(usersList.get(position).getImageProfile()).into(holder.userImage);
        }

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView userName, userEmail;
        private ImageView userImage;

        public MyViewHolder(View view) {
            super(view);
            userName = (TextView) view.findViewById(R.id.user_name);
            userEmail = (TextView) view.findViewById(R.id.user_email);
            userImage = (ImageView) view.findViewById(R.id.user_image);

            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            String id=usersList.get(position).getId();
            Intent intent=new Intent(mContext,ProfileActivity.class);
            intent.putExtra("user_id",id);
            mContext.startActivity(intent);

        }
    }
}