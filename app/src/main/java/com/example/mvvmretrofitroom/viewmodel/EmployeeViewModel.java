package com.example.mvvmretrofitroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmretrofitroom.model.EmployeeBeen;
import com.example.mvvmretrofitroom.repository.EmployeeRepository;

import java.util.List;

import static com.example.mvvmretrofitroom.MyApplication.application;

public class EmployeeViewModel extends AndroidViewModel {
    private EmployeeRepository employeeRepository;
    private LiveData<List<EmployeeBeen>> employeeBeenLiveData;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        employeeRepository = new EmployeeRepository(application);
        //get data from local db using repo instance
        this.employeeBeenLiveData = employeeRepository.getmAllEmployee();
    }

    public void queryAPI() {
        //get user repo
        employeeRepository = new EmployeeRepository(application);
        //get api data using repo instance
        this.employeeBeenLiveData = employeeRepository.getEmployees();
    }

    public LiveData<List<EmployeeBeen>> getEmployeeBeenLiveData() {
        return employeeBeenLiveData;
    }
}
