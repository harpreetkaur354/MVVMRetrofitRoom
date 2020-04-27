package com.example.mvvmretrofitroom.roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mvvmretrofitroom.model.UsersBeen;

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
