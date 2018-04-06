package com.example.tony.mohamedtonytask.webServices;

import com.example.tony.mohamedtonytask.webServices.request.CreateUserModel;
import com.example.tony.mohamedtonytask.webServices.response.LoginResponse;
import com.example.tony.mohamedtonytask.webServices.response.StatusResponse;
import com.example.tony.mohamedtonytask.webServices.response.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitService {

    @POST("users/add")
    Call<StatusResponse> createUser(@Body CreateUserModel createUserModel);

    @GET("login/{username}/{password}")
    Call<LoginResponse> loginUser(@Path("username")String username, @Path("password") String password);

    @GET("users/current/{userid}")
    Call<List<UserData>> getUserData(@Path("userid")String userid);

    @GET("allusers")
    Call<List<UserData>> getAllUsers();

    @POST("users/update_imageprofile/{id}")
    @FormUrlEncoded
    Call<StatusResponse> updateImage(@Path("id")String userID,@Field("image_profile") String base64);

}