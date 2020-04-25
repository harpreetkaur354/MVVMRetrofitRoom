package com.example.mvvmroomretrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmroomretrofit.R;
import com.example.mvvmroomretrofit.model.UsersBeen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.MyHolder> {
    private Context mContext;
    private ArrayList<UsersBeen.Datum> usersBeenArrayList;

    public UsersListAdapter(Context context, ArrayList<UsersBeen.Datum> usersBeenArrayList)
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
        Picasso.with(mContext).load(usersBeenArrayList.get(position).getAvatar()).into(holder.ivUserProfile);
        holder.tvEmail.setText(usersBeenArrayList.get(position).getEmail());
        String firstName = usersBeenArrayList.get(position).getFirstName();
        String lastName = usersBeenArrayList.get(position).getLastName();
        String userName = firstName + lastName;
        holder.tvUserName.setText(userName);
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
