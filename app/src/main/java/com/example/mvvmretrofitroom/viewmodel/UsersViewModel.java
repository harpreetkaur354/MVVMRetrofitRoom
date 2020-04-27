package com.example.mvvmretrofitroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmretrofitroom.model.UsersBeen;
import com.example.mvvmretrofitroom.repository.UsersRepository;

import static com.example.mvvmretrofitroom.MyApplication.application;

public class UsersViewModel extends AndroidViewModel {
    private UsersRepository usersRepository;
    private LiveData<UsersBeen> usersBeenLiveData;
    public UsersViewModel(@NonNull Application application) {
        super(application);
        usersRepository = new UsersRepository(application);
        //get data from local db using repo instance
        this.usersBeenLiveData = usersRepository.getmAllUsers();
    }

    public void queryAPI()
    {
        //get user repo
        usersRepository = new UsersRepository(application);
        //get api data using repo instance
        this.usersBeenLiveData = usersRepository.getUsers();
    }

    public LiveData<UsersBeen> getUsersBeenLiveData()
    {
        return usersBeenLiveData;
    }
}
