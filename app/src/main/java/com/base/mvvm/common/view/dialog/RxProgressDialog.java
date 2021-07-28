/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 10:08 AM
 *
 */

package com.base.mvvm.common.view.dialog;

import android.content.Context;

import com.base.mvvm.R;

import io.reactivex.SingleTransformer;

/**
 * RxProgressDialog
 */
public class RxProgressDialog {

  private Context context;
  private ProgressDialog progressDialog;

  public RxProgressDialog(Context context) {
    this.context = context;
  }

  public <T> SingleTransformer<T, T> applyDialog() {
    createDialog(context);
    return upstream -> upstream.doOnSubscribe(disposable -> {
      if (progressDialog != null) {
        if (!progressDialog.isShowing()) {
          progressDialog.show();
        }
      }
    }).doFinally(() -> {
      if (progressDialog != null) {
        if (progressDialog.isShowing()) {
          progressDialog.dismiss();
        }
        progressDialog = null;
      }
    });
  }

  private void createDialog(Context context) {
    if (progressDialog == null) {
      progressDialog = new ProgressDialog(context, R.style.ProgressDialog);
      progressDialog.setCancelable(false);
      progressDialog.setCanceledOnTouchOutside(false);
    }
  }
}