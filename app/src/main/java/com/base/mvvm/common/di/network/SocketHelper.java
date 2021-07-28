package com.base.mvvm.common.di.network;

import android.content.Context;

import com.base.mvvm.R;
import com.base.mvvm.common.utils.exception.network.AppException;

public class SocketHelper {

  public static void handleSocketHelper(Context context) {
    throw new AppException(context.getString(R.string.error_system));
  }
}
