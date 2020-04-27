package com.example.mvvmretrofitroom.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "user_bean")
public class UsersBeen {
    @ColumnInfo(name = "page")
    @SerializedName("page")
    @Expose
    private Integer page;

    @ColumnInfo(name = "per_page")
    @SerializedName("per_page")
    @Expose
    private Integer perPage;

    @ColumnInfo(name = "total")
    @SerializedName("total")
    @Expose
    private Integer total;

    @ColumnInfo(name = "total_pages")
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @Nullable
    @TypeConverters(UserBeanConvertor.class)
    @SerializedName("data")
    @Expose
    private List<Datum> data;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    @Entity(tableName = "data")
    public class Datum {

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        @SerializedName("id")
        @Expose
        private Integer id;
        @NonNull
        @ColumnInfo(name = "email")
        @SerializedName("email")
        @Expose
        private String email;

        @NonNull
        @ColumnInfo(name = "first_name")
        @SerializedName("first_name")
        @Expose
        private String firstName;

        @ColumnInfo(name = "last_name")
        @SerializedName("last_name")
        @Expose
        private String lastName;

        @ColumnInfo(name = "avatar")
        @SerializedName("avatar")
        @Expose
        private String avatar;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

    }
}


