/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 10:08 AM
 *
 */

package com.base.mvvm.common.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.base.mvvm.R;
import com.base.mvvm.databinding.DialogLoadingBinding;

/**
 * ProgressDialog
 */
public class ProgressCustomViewDialog extends Dialog {

  public ProgressCustomViewDialog(@NonNull Context context) {
    super(context, R.style.StyleCommonDialog);
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    DialogLoadingBinding binding = DataBindingUtil
        .inflate(LayoutInflater.from(getContext()), R.layout.dialog_loading,
            null, false);

    setContentView(binding.getRoot());
    setCanceledOnTouchOutside(false);

    setCanceledOnTouchOutside(false);
    setCancelable(false);
    super.onCreate(savedInstanceState);
  }
}