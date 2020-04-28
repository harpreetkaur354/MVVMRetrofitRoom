package com.example.mvvmretrofitroom.retrofit;

import com.example.mvvmretrofitroom.model.EmployeeBeen;
import com.example.mvvmretrofitroom.model.UsersBeen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/users")
    Call<List<UsersBeen>> getUsers();

    @GET("/posts")
    Call<List<EmployeeBeen>> getEmployee();
}
