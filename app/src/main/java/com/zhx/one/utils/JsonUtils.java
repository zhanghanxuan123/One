package com.zhx.one.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
/**
 * Author   :zhx
 * Create at 2016/12/6
 * Description:
 */
public class JsonUtils {
    public static <T> T getEntity(String str, Class<T> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(str,classOfT);
    }

    public static String EntityToJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static <T> T getEntityList(String str, Type typeOfT) {
        Gson gson = new Gson();
        return gson.fromJson(str, typeOfT);
    }


}
