package com.example.mvvmretrofitroom.roomdb;

import android.os.AsyncTask;

public class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final UserInfoDao mDao;

    PopulateDbAsync(UserInfoRoomDatabase db) {
        mDao = db.userInfoDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
//        mDao.deleteAll();
        
       /* User user = new User("Chandra","SW");
        mDao.insert(user);
        user = new User("Mohan","student");
        mDao.insert(user);*/
        return null;
    }
}
