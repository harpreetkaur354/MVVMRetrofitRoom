package com.example.mvvmroomretrofit.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmroomretrofit.MyApplication;
import com.example.mvvmroomretrofit.model.UsersBeen;
import com.example.mvvmroomretrofit.repository.UsersRepository;

import static com.example.mvvmroomretrofit.MyApplication.application;

public class UsersViewModel extends AndroidViewModel {
    private UsersRepository usersRepository;
    private LiveData<UsersBeen> usersBeenLiveData;
    private LiveData<UsersBeen> usersBeenRoomData;
    public UsersViewModel(@NonNull Application application) {
        super(application);
        usersRepository = new UsersRepository(application);
        this.usersBeenLiveData = usersRepository.getmAllUsers();
     //   this.usersBeenRoomData = usersRepository.getUsers();

        //  this.usersBeenRoomData = usersRepository.getmAllUsers();
//        Log.e("View Model","Data--: "+usersBeenRoomData.getValue().getPage());
    }

    public void queryAPI()
    {
        //get user repo
        usersRepository = new UsersRepository(application);
        this.usersBeenLiveData = usersRepository.getmAllUsers();
       // this.usersBeenRoomData = usersRepository.getUsers();
    }

    public LiveData<UsersBeen> getUsersBeenLiveData()
    {
       /* if(usersBeenRoomData.getValue() == null)
        {
            return usersBeenLiveData;
        }*/
        return usersBeenLiveData;
    }
}
