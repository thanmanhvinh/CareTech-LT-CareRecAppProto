/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/20/19 11:13 AM
 *
 */

package com.base.mvvm.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast Utils
 */
public class ToastUtils {

  private static Toast sToast;

  public static void showToast(Context context, String message) {
    if (sToast != null) {
      sToast.cancel();
    }
    sToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
    sToast.show();
  }
}
