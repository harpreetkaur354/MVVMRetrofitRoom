package com.example.mvvmretrofitroom.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmretrofitroom.R;
import com.example.mvvmretrofitroom.adapters.UsersListAdapter;
import com.example.mvvmretrofitroom.model.UsersBeen;
import com.example.mvvmretrofitroom.utils.NetworkStatus;
import com.example.mvvmretrofitroom.viewmodel.UsersViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvUsersList;
    ProgressBar progressCircular;
    private LinearLayoutManager layoutManager;
    private UsersListAdapter usersListAdapter;
    private ArrayList<UsersBeen> usersBeenArrayList = new ArrayList<>();
    private UsersViewModel usersViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initial method call
        initView();
        if(NetworkStatus.getInstance(this).isOnline())
        {
            usersViewModel.queryAPI();
        }
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
        usersViewModel.getUsersBeenLiveData().observe(this, new Observer<List<UsersBeen>>() {
            @Override
            public void onChanged(List<UsersBeen> usersBeen) {
                if(usersBeen != null)
                {
                    progressCircular.setVisibility(View.GONE);
                    usersBeenArrayList.addAll(usersBeen);
                    usersListAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
