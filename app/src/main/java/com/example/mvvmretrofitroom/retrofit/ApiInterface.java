package com.example.mvvmretrofitroom.retrofit;

import com.example.mvvmretrofitroom.model.UsersBeen;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("users?page=2")
    Call<UsersBeen> getUsers();
}
