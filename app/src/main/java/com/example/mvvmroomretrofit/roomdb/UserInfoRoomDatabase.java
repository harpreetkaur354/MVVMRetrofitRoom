package com.example.mvvmroomretrofit.roomdb;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mvvmroomretrofit.model.Users;
import com.example.mvvmroomretrofit.model.UsersBeen;

@Database(entities = {UsersBeen.class}, version = 1, exportSchema = true)
public abstract class UserInfoRoomDatabase extends RoomDatabase {
    public abstract UserInfoDao userInfoDao();
    private static UserInfoRoomDatabase INSTANCE;

    public static UserInfoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserInfoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            UserInfoRoomDatabase.class, "userInfoDatabase")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback =
            new Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
}
