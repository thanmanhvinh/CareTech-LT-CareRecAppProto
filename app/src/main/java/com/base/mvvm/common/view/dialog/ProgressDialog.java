/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 10:08 AM
 *
 */

package com.base.mvvm.common.view.dialog;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.base.mvvm.R;

/**
 * ProgressDialog
 */
public class ProgressDialog extends AlertDialog {

  public ProgressDialog(@NonNull Context context) {
    super(context, R.style.ProgressDialog);
  }

  public ProgressDialog(@NonNull Context context, int themeResId) {
    super(context, themeResId);
  }

  private int idColorProgress;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    FrameLayout layout = new FrameLayout(getContext());
    ProgressBar progressBar = new ProgressBar(getContext());
    progressBar.getIndeterminateDrawable()
        .setColorFilter(ContextCompat.getColor(getContext(), R.color.progress_icon), PorterDuff.Mode.SRC_IN);
    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
        FrameLayout.LayoutParams.WRAP_CONTENT);
    layoutParams.gravity = Gravity.CENTER;
    layout.addView(progressBar, layoutParams);
    setView(layout);
    setCanceledOnTouchOutside(false);
    setCancelable(false);
    super.onCreate(savedInstanceState);
  }

  public int getIdColorProgress() {
    if(idColorProgress == 0 )
      idColorProgress = R.color.progress_icon;
    return idColorProgress;
  }

  public void setIdColorProgress(int idColorProgress) {
    this.idColorProgress = idColorProgress;
  }
}