/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/26/19 9:25 AM
 *
 */

package com.base.mvvm.common.data.remote;

import com.base.mvvm.common.data.local.SharedPrefsHelper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * service setting  AuthorizationInterceptor
 */
public class AuthorizationInterceptor implements Interceptor {

  private SharedPrefsHelper appPrefs;

  public AuthorizationInterceptor(SharedPrefsHelper appPrefs) {
    this.appPrefs = appPrefs;
  }

  @NotNull
  @Override
  public Response intercept(@NotNull Chain chain) throws IOException {
    Request.Builder builder = chain.request().newBuilder();
    return chain.proceed(builder.build());
  }
}
