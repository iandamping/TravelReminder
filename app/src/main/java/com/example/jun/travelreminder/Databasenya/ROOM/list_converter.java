package com.example.jun.travelreminder.Databasenya.ROOM;

import android.arch.persistence.room.TypeConverter;

import com.example.jun.travelreminder.model.news.Article;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class list_converter {

    static Gson gson = new Gson();

    @TypeConverter
    public static List<Article> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Article>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<Article> someObjects) {
        return gson.toJson(someObjects);
    }
}
