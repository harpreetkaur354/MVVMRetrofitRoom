package com.example.mvvmretrofitroom.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmretrofitroom.model.EmployeeBeen;
import com.example.mvvmretrofitroom.retrofit.ApiClient;
import com.example.mvvmretrofitroom.retrofit.ApiInterface;
import com.example.mvvmretrofitroom.roomdb.EmployeeInfoDao;
import com.example.mvvmretrofitroom.roomdb.UserInfoRoomDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeRepository {
    private static final String TAG = EmployeeRepository.class.getSimpleName();
    private ApiInterface apiInterface;
    public EmployeeInfoDao employeeInfoDao;
    public LiveData<List<EmployeeBeen>> mAllEmployee;
    private UserInfoRoomDatabase userInfoRoomDatabase;

    public EmployeeRepository(Application application)
    {
        userInfoRoomDatabase = UserInfoRoomDatabase.getDatabase(application);
        employeeInfoDao = userInfoRoomDatabase.employeeInfoDao();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        mAllEmployee = employeeInfoDao.getAllEmployees();
    }

    public LiveData<List<EmployeeBeen>> getmAllEmployee()
    {
        return mAllEmployee;
    }

    public LiveData<List<EmployeeBeen>> getEmployees()
    {

        System.out.println("----call1----");
        final MutableLiveData<List<EmployeeBeen>> data = new MutableLiveData<>();

        apiInterface.getEmployee().enqueue(new Callback<List<EmployeeBeen>>() {
            @Override
            public void onResponse(@NonNull Call<List<EmployeeBeen>> call, @NonNull Response<List<EmployeeBeen>> response) {
                Log.d(TAG,"Response: "+response);
                if(response.body()!= null)
                {
                    insertUser(response.body());
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<EmployeeBeen>> call,@NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public void insertUser (List<EmployeeBeen> users) {

        new insertAsyncTask(employeeInfoDao).execute(users);
    }

    private static class insertAsyncTask extends AsyncTask<List<EmployeeBeen>, Void, Void> {

        private EmployeeInfoDao mAsyncTaskDao;

        insertAsyncTask(EmployeeInfoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<EmployeeBeen>... params) {
//            Log.e("Repository","Users Data"+params[0].getTitle());
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
