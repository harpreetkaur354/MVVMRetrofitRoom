package com.example.mvvmroomretrofit.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmroomretrofit.model.Users;
import com.example.mvvmroomretrofit.model.UsersBeen;
import com.example.mvvmroomretrofit.retrofit.ApiClient;
import com.example.mvvmroomretrofit.retrofit.ApiInterface;
import com.example.mvvmroomretrofit.roomdb.UserInfoDao;
import com.example.mvvmroomretrofit.roomdb.UserInfoRoomDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository {
    private static final String TAG = UsersRepository.class.getSimpleName();
    private ApiInterface apiInterface;
    public UserInfoDao userInfoDao;
    public LiveData<UsersBeen> mAllUsers;
    private UserInfoRoomDatabase userInfoRoomDatabase;

    public UsersRepository(Application application)
    {
        userInfoRoomDatabase = UserInfoRoomDatabase.getDatabase(application);
        userInfoDao = userInfoRoomDatabase.userInfoDao();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        mAllUsers = userInfoDao.getAllUsers();
    }

    public LiveData<UsersBeen> getmAllUsers() {
        return mAllUsers;
    }

    public LiveData<UsersBeen> getUsers()
    {

            System.out.println("----call1----");
            final MutableLiveData<UsersBeen> data = new MutableLiveData<>();

            apiInterface.getUsers().enqueue(new Callback<UsersBeen>() {
                @Override
                public void onResponse(@NonNull Call<UsersBeen> call,@NonNull Response<UsersBeen> response) {
                    Log.d(TAG,"Response: "+response);
                    if(response.body()!= null)
                    {
                        Users users = new Users();
                        users.setPage(response.body().getPage());
                        users.setPerPage(response.body().getPerPage());
                        users.setTotal(response.body().getTotal());
                        users.setTotalPages(response.body().getTotalPages());

                        insertUser(response.body());
                        data.setValue(response.body());
                        Log.e(TAG,"Total Employee :"+response.body().getData().size());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<UsersBeen> call,@NonNull Throwable t) {
                    data.setValue(null);
                }
            });
            return data;
        }



    public void insertUser (UsersBeen users) {

        new insertAsyncTask(userInfoDao).execute(users);
    }

    private static class insertAsyncTask extends AsyncTask<UsersBeen, Void, Void> {

        private UserInfoDao mAsyncTaskDao;

        insertAsyncTask(UserInfoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final UsersBeen... params) {
            Log.e("Repository","Users Data"+params[0].getPage()+" "+params[0].getPerPage());
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
