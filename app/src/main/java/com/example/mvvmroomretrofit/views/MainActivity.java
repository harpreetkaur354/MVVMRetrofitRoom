package com.example.mvvmroomretrofit.views;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mvvmroomretrofit.R;
import com.example.mvvmroomretrofit.adapters.UsersListAdapter;
import com.example.mvvmroomretrofit.model.UsersBeen;
import com.example.mvvmroomretrofit.viewmodel.UsersViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvUsersList;
    ProgressBar progressCircular;
    private LinearLayoutManager layoutManager;
    private UsersListAdapter usersListAdapter;
    private ArrayList<UsersBeen.Datum> usersBeenArrayList = new ArrayList<>();
    private UsersViewModel usersViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initial method call
        initView();
        usersViewModel.queryAPI();
        getSetUsersData();


    }

    public void initView()
    {
        rvUsersList = findViewById(R.id.rvUsersList);

        progressCircular = findViewById(R.id.progressCircular);

        layoutManager = new LinearLayoutManager(MainActivity.this);
        rvUsersList.setLayoutManager(layoutManager);
        rvUsersList.setHasFixedSize(true);
        //set adapter in recycler view
        usersListAdapter = new UsersListAdapter(MainActivity.this,usersBeenArrayList);
        rvUsersList.setAdapter(usersListAdapter);
        //view model
        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);

    }

    private void getSetUsersData()
    {
        usersViewModel.getUsersBeenLiveData().observe(this, new Observer<UsersBeen>() {
            @Override
            public void onChanged(UsersBeen usersBeen) {
                if(usersBeen != null)
                {
                    progressCircular.setVisibility(View.GONE);
                    List<UsersBeen.Datum> employeeList = usersBeen.getData();
                    usersBeenArrayList.addAll(employeeList);
                    usersListAdapter.notifyDataSetChanged();
                }
            }
        });

    }
}
