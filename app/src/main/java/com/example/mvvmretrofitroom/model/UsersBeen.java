package com.example.mvvmretrofitroom.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "user_bean")
public class UsersBeen {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    private Integer id;

    @NonNull
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;

    @NonNull
    @ColumnInfo(name = "username")
    @SerializedName("username")
    @Expose
    private String username;

    @NonNull
    @ColumnInfo(name = "email")
    @SerializedName("email")
    @Expose
    private String email;

    @NonNull
    @ColumnInfo(name = "phone")
    @SerializedName("phone")
    @Expose
    private String phone;

    @NonNull
    @ColumnInfo(name = "website")
    @SerializedName("website")
    @Expose
    private String website;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}


