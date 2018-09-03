package com.example.jun.travelreminder.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by mzennis on 9/19/16.
 */
public class PreferenceHelper {

    //	public static final String KEY_LANGUAGE = "key_language";
//	public static final String FIRST_LAUNCH = "first_launch";
//	public static final String IS_LOGIN = "is_login";
//	public static final String TOKEN = "token";
//	public static final String USER_ID = "user_id";
    public static final String BASE_URL = "base_url";

    //	public static final String USERNAME = "username";
//	public static final String PASSWORD = "password";
//
//	public static final String FORGOTPASS= "forgot_pass";
//
//	public static final String LANG = "lang";
    public static final String IS_ON = "1";
    public static final String IS_OFF = "0";
    public static final String WIFI_ON = "wifi_on";
    public static final String BROADBAND_ON = "broadband_on";

    private SharedPreferences sharedPreferences;

    public PreferenceHelper(Context context) {
        sharedPreferences = getSharedPreference(context);
    }

    public SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void putString(String key, String isi) {
        sharedPreferences.edit().putString(key, isi).apply();
    }

    public void putBoolean(String key, boolean isi) {
        sharedPreferences.edit().putBoolean(key, isi).apply();
    }

    public void putInt(String key, int num) {
        sharedPreferences.edit().putInt(key, num).apply();
    }

    public void putLong(String key, long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }


//	public void putDouble( final String key, final double value) {
//        sharedPreferences.edit().putLong(key, Double.doubleToRawLongBits(value)).apply();
//    }
//	public double getDouble(final String key, final double defaultValue) {
//		return Double.longBitsToDouble(key, Double.doubleToLongBits(defaultValue));
//	}

    public <T> void putList(String key, List<T> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        putString(key, json);
    }

    public <T> void putObj(String key, T obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        putString(key, json);
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public <T> List<T> getList(String key, final Class<T[]> cls) {
        Gson gson = new Gson();
        String json = getString(key);
        T[] list = gson.fromJson(json, cls);
        return list != null ? Arrays.asList(list) : null;
    }

    public <T> T getObj(String key, final Class<T> cls) {
        Gson gson = new Gson();
        String json = getString(key);
        T obj = gson.fromJson(json, cls);
        return obj != null ? obj : null;
    }

    public void clear(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

    public void logout() {
        Map<String, ?> keys = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            if (!entry.getKey().equals(BASE_URL))
                sharedPreferences.edit().remove(entry.getKey()).apply();
        }
    }
}
