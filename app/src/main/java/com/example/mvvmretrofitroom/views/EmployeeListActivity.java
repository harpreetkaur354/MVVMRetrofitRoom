package com.example.mvvmretrofitroom.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmretrofitroom.R;
import com.example.mvvmretrofitroom.adapters.EmployeeListAdapter;
import com.example.mvvmretrofitroom.model.EmployeeBeen;
import com.example.mvvmretrofitroom.utils.NetworkStatus;
import com.example.mvvmretrofitroom.viewmodel.EmployeeViewModel;

import java.util.ArrayList;

public class EmployeeListActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private RecyclerView rvEmployeeList;
    private LinearLayoutManager layoutManager;
    private EmployeeListAdapter employeeListAdapter;
    private ArrayList<EmployeeBeen> employeeBeenArrayList = new ArrayList<>();
    private EmployeeViewModel employeeViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_list);
        //initial method call
        initView();
        if(NetworkStatus.getInstance(this).isOnline())
        {
            employeeViewModel.queryAPI();
        }

        getSetEmployeeData();
    }
    public void initView()
    {
        progressBar = findViewById(R.id.progressCircular);
        rvEmployeeList = findViewById(R.id.rvEmployeeList);
        layoutManager = new LinearLayoutManager(EmployeeListActivity.this);
        rvEmployeeList.setLayoutManager(layoutManager);
        rvEmployeeList.setHasFixedSize(true);
        //set adapter in recycler view
        employeeListAdapter = new EmployeeListAdapter(EmployeeListActivity.this,employeeBeenArrayList);
        rvEmployeeList.setAdapter(employeeListAdapter);
        //view model
        employeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
    }

    public void getSetEmployeeData()
    {
       employeeViewModel.getEmployeeBeenLiveData().observe(this, employeeBeen -> {
           if(employeeBeen!=null)
           {
               progressBar.setVisibility(View.GONE);
               employeeBeenArrayList.addAll(employeeBeen);
               employeeListAdapter.notifyDataSetChanged();
           }
       });
    }
}
