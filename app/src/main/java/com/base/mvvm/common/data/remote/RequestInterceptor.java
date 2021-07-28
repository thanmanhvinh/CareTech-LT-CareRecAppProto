/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/26/19 9:25 AM
 *
 */

package com.base.mvvm.common.data.remote;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * RequestInterceptor
 */
public class RequestInterceptor implements Interceptor {

  @NotNull
  @Override
  public Response intercept(Chain chain) throws IOException {
    Request originalRequest = chain.request();
    HttpUrl originalHttpUrl = originalRequest.url();

    HttpUrl url = originalHttpUrl.newBuilder()
        .build();

    Request request = originalRequest.newBuilder().url(url).build();
    return chain.proceed(request);
  }
}