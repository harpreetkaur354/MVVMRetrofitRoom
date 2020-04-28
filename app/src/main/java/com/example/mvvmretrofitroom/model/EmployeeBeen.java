package com.example.mvvmretrofitroom.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "employee_been")
public class EmployeeBeen {
    @NonNull
    @ColumnInfo(name = "userId")
    @SerializedName("userId")
    @Expose
    private Integer userId;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    private Integer id;

    @NonNull
    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    private String title;

    @NonNull
    @ColumnInfo(name = "body")
    @SerializedName("body")
    @Expose
    private String body;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
