/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/26/19 8:44 AM
 *
 */

package com.base.mvvm.common.utils;


import com.base.mvvm.BuildConfig;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Log Utils
 */
public class LogUtils {

  public static final String TAG = LogUtils.class.getSimpleName();

  public static void init() {
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

  public static void d(String message) {
    try {
      Timber.d(getCallerClassName() + " : " + message);
    } catch (Exception e) {
      Timber.d(getCallerClassName() + " : " + message);
    }
  }

  public static void d(String message, Throwable t) {
    try {

      Timber.d(t, getCallerClassName() + " : " + message);
    } catch (Exception e) {
      Timber.d(t, getCallerClassName() + " : " + message);
    }
  }

  public static void enter() {
    d("");
  }

  private static String getCallerClassName() {

    ArrayList<String> data = new ArrayList<>();
    data.add("SingleLiveEvent");
    data.add("SingleLiveEvent$1");
    data.add("LogUtils");

    data.add(LogUtils.class.getName());

    data.add("java.lang.reflect.Method");
    data.add("java.lang.Thread");

    StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
    for (int i = 1; i < stElements.length; i++) {
      StackTraceElement ste = stElements[i];
      String name = ste.getClassName();
      String method = ste.getMethodName();
      int line = ste.getLineNumber();

      if (!isAndroid(name) && !data.contains(name)) {
        if (name.contains(".")) {
          name = name.substring(name.lastIndexOf(".") + 1);
        }
        return String.format("%s.(%s.java:%s)", method, name, line);
      }

    }

    return "";
  }

  private static boolean isAndroid(String name) {
    return name.startsWith("com.android") || name.startsWith("android") || name.startsWith("androidx");
  }
}
