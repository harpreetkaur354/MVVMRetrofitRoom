package com.example.mvvmretrofitroom.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmretrofitroom.model.UsersBeen;
import com.example.mvvmretrofitroom.retrofit.ApiClient;
import com.example.mvvmretrofitroom.retrofit.ApiInterface;
import com.example.mvvmretrofitroom.roomdb.UserInfoDao;
import com.example.mvvmretrofitroom.roomdb.UserInfoRoomDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository {
    private static final String TAG = UsersRepository.class.getSimpleName();
    private ApiInterface apiInterface;
    public UserInfoDao userInfoDao;
    public LiveData<List<UsersBeen>> mAllUsers;
    private UserInfoRoomDatabase userInfoRoomDatabase;

    public UsersRepository(Application application)
    {
        userInfoRoomDatabase = UserInfoRoomDatabase.getDatabase(application);
        userInfoDao = userInfoRoomDatabase.userInfoDao();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        mAllUsers = userInfoDao.getAllUsers();
    }

    public LiveData<List<UsersBeen>> getmAllUsers() {
        return mAllUsers;
    }

    public LiveData<List<UsersBeen>> getUsers()
    {

            System.out.println("----call1----");
            final MutableLiveData<List<UsersBeen>> data = new MutableLiveData<>();

            apiInterface.getUsers().enqueue(new Callback<List<UsersBeen>>() {
                @Override
                public void onResponse(@NonNull Call<List<UsersBeen>> call,@NonNull Response<List<UsersBeen>> response) {
                    Log.e(TAG,"Response: "+response);
                    if(response.body()!= null)
                    {
                        insertUser(response.body());
                        data.setValue(response.body());
//                        Log.e(TAG,"Total Employee :"+response.body().getData().size());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<UsersBeen>> call,@NonNull Throwable t) {
                    Log.e("--apiError---",t.getMessage());
                    data.setValue(null);
                }
            });
            return data;
        }



    private void insertUser(List<UsersBeen> users) {

        new insertAsyncTask(userInfoDao).execute(users);
    }

    private static class insertAsyncTask extends AsyncTask<List<UsersBeen>, Void, Void> {

        private UserInfoDao mAsyncTaskDao;

        insertAsyncTask(UserInfoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<UsersBeen>... params) {
//            Log.e("Repository","Users Data"+params[0].getPage()+" "+params[0].getPerPage());
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
