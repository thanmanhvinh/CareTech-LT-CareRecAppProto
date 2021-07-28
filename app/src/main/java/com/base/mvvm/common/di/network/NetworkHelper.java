/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/26/19 9:28 AM
 *
 */

package com.base.mvvm.common.di.network;

import android.content.Context;

import com.base.mvvm.R;
import com.base.mvvm.common.data.remote.response.BaseResponse;
import com.base.mvvm.common.utils.exception.network.AppException;
import com.google.gson.Gson;

import okhttp3.Response;

/**
 * NetworkHelper
 */
public class NetworkHelper {

  public static void handleNetworkError(Context context, Response response, Gson gson) {

    if (response.body() != null) {
      BaseResponse errorResponse = null;
      try {
        errorResponse = gson.fromJson(response.body().string(), BaseResponse.class);
      } catch (Exception ignored) {
      }
      if (errorResponse != null) {
        throw new AppException(gson.toJson(errorResponse));
      } else {
        throw new AppException(context.getString(R.string.networkError));
      }
    }
  }
}
