/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/26/19 9:25 AM
 *
 */

package com.base.mvvm.common.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPrefsHelper
 */
public class SharedPrefsHelper {

  private static final String ACCESS_TOKEN = "token";
  public static final String PREF_KEY_LOGIN = "_login";
  private static final String PREF_KEY_USER_ID = "_UserId";
  public static final String PREF_EMAIL = "_email";

  private SharedPreferences mSharedPreferences;

  public SharedPrefsHelper(Context context) {

    mSharedPreferences = context.getSharedPreferences("PingsApp", Context.MODE_PRIVATE);
  }

  public void put(String key, String value) {
    mSharedPreferences.edit().putString(key, value).apply();
  }

  public void put(String key, int value) {
    mSharedPreferences.edit().putInt(key, value).apply();
  }

  public void put(String key, float value) {
    mSharedPreferences.edit().putFloat(key, value).apply();
  }

  public void put(String key, boolean value) {
    mSharedPreferences.edit().putBoolean(key, value).apply();
  }

  public void put(String key, long value) {
    mSharedPreferences.edit().putLong(key, value).apply();
  }

  public String get(String key, String defaultValue) {
    return mSharedPreferences.getString(key, defaultValue);
  }

  public long get(String key, long defaultValue) {
    return mSharedPreferences.getLong(key, defaultValue);
  }

  public int get(String key, int defaultValue) {
    return mSharedPreferences.getInt(key, defaultValue);
  }

  public Float get(String key, float defaultValue) {
    return mSharedPreferences.getFloat(key, defaultValue);
  }

  public Boolean get(String key, boolean defaultValue) {
    return mSharedPreferences.getBoolean(key, defaultValue);
  }

  private void remove(String key) {
    mSharedPreferences.edit().remove(key).apply();
  }

  public void clearAccessToken() {
    remove(ACCESS_TOKEN);
  }

  public void clearLoginData() {
    remove(ACCESS_TOKEN);
    remove(PREF_KEY_USER_ID);
    put(PREF_KEY_LOGIN, false);
    remove(PREF_EMAIL);
  }

  public String getAccessToken() {
    return get(ACCESS_TOKEN, "");
  }

  public void setAccessToken(String accessToken) {
    put(ACCESS_TOKEN, accessToken);
  }

  public int getUserId() {
    return get(PREF_KEY_USER_ID, -1);
  }

  public void setUserId(int userId) {
    put(PREF_KEY_USER_ID, userId);
  }

}
