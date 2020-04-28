package com.example.mvvmretrofitroom.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmretrofitroom.R;
import com.example.mvvmretrofitroom.model.EmployeeBeen;

import java.util.ArrayList;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<EmployeeBeen> employeeBeenArrayList;

    public EmployeeListAdapter(Context context, ArrayList<EmployeeBeen> employeeBeenArrayList)
    {
        this.mContext = context;
        this.employeeBeenArrayList = employeeBeenArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       holder.tvUserName.setText(employeeBeenArrayList.get(position).getTitle());
       holder.tvEmail.setText(employeeBeenArrayList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return employeeBeenArrayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName, tvEmail;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}
