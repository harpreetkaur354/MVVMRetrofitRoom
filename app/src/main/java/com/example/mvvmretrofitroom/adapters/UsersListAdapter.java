package com.example.mvvmretrofitroom.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmretrofitroom.R;
import com.example.mvvmretrofitroom.model.UsersBeen;
import com.example.mvvmretrofitroom.views.EmployeeListActivity;

import java.util.ArrayList;
import java.util.List;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.MyHolder> {
    private Context mContext;
    private ArrayList<UsersBeen> usersBeenArrayList;

    public UsersListAdapter(Context context, ArrayList<UsersBeen> usersBeenArrayList)
    {
        this.mContext = context;
        this.usersBeenArrayList = usersBeenArrayList;
    }

    @NonNull
    @Override
    public UsersListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersListAdapter.MyHolder holder, int position) {
        holder.tvEmail.setText(usersBeenArrayList.get(position).getEmail());
//        String name = usersBeenArrayList.get(position).getName();
        holder.tvUserName.setText(usersBeenArrayList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, EmployeeListActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersBeenArrayList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        ImageView ivUserProfile;
        TextView tvUserName, tvEmail;
        MyHolder(@NonNull View itemView) {
            super(itemView);
            ivUserProfile = itemView.findViewById(R.id.ivUserProfile);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}
