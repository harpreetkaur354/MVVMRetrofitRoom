package com.example.mvvmretrofitroom.roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mvvmretrofitroom.model.EmployeeBeen;

import java.util.List;

@Dao
public interface EmployeeInfoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<EmployeeBeen> employees);

    @Query("SELECT * from employee_been ORDER BY id ASC")
    LiveData<List<EmployeeBeen>> getAllEmployees();
}
