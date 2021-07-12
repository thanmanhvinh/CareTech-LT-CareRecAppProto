/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 10:08 AM
 *
 */

package com.base.mvvm.common.utils.exception.network;

import android.content.Context;

import com.base.mvvm.R;
import com.base.mvvm.common.utils.ToastUtils;

public class NetworkUtils {

  public static void showToastNetworkError(Context context, Throwable throwable) {
    if (throwable instanceof NoNetworkException) {
      ToastUtils.showToast(context, context.getString(R.string.networkError));
    }
  }

  public static boolean isNetWorkException(Throwable throwable) {
    return throwable instanceof NoNetworkException;
  }
}
