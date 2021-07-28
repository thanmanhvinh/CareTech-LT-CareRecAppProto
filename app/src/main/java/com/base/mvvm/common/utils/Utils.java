/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 10:08 AM
 *
 */

package com.base.mvvm.common.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.base.mvvm.R;
import com.base.mvvm.common.data.remote.response.BaseResponse;
import com.base.mvvm.common.utils.exception.network.NetworkUtils;
import com.google.gson.Gson;

public class Utils {

    private static final String TAG  = Utils.class.getSimpleName();

    /**
     * hideAllKeyboard
     */
    public static void hideAllKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
    /**
     * is NetWork
     */
    public static boolean isNetWork(Context context) {
        if (isNetworkConnected(context)) {
            return true;
        } else {
            ToastUtils.showToast(context, context.getString(R.string.networkError));
            return false;
        }
    }

    /**
     * isNetworkConnected
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected() &&
                (networkInfo.getType() == ConnectivityManager.TYPE_WIFI
                        || networkInfo.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    /**
     * @return Response not null
     */
    public static BaseResponse parserError(Throwable throwable, Context context) {
        BaseResponse errorResponse;
        try {
            Gson gson = new Gson();
            errorResponse = gson.fromJson(throwable.getMessage(), BaseResponse.class);
        } catch (Exception ignored) {
            errorResponse = new BaseResponse();
            errorResponse.setSuccess(false);
            if (NetworkUtils.isNetWorkException(throwable)) {
                errorResponse.setErrorMessage(context.getString(R.string.networkError));
            } else {
                errorResponse.setErrorMessage(context.getString(R.string.error_system));
            }
        }
        return errorResponse;
    }

}
