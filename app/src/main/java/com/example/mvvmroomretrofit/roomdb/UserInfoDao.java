package com.example.mvvmroomretrofit.roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mvvmroomretrofit.model.Users;
import com.example.mvvmroomretrofit.model.UsersBeen;

import java.util.List;

@Dao
public interface UserInfoDao
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(UsersBeen users);

   @Query("SELECT * from user_bean ORDER BY id ASC")
    LiveData<UsersBeen> getAllUsers();

   @Query("DELETE FROM user_bean")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(UsersBeen users);
}
