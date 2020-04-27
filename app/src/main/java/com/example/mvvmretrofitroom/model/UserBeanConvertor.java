package com.example.mvvmretrofitroom.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class UserBeanConvertor {
    @TypeConverter
    public static List<UsersBeen.Datum> storedStringToMyObjects(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<UsersBeen.Datum>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String myObjectsToStoredString(List<UsersBeen.Datum> myObjects) {
        Gson gson = new Gson();
        return gson.toJson(myObjects);
    }
}
