package com.example.mvvmroomretrofit.retrofit;

import com.example.mvvmroomretrofit.model.UsersBeen;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("users?page=2")
    Call<UsersBeen> getUsers();
}
