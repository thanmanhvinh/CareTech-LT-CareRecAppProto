/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 10:08 AM
 *
 */

package com.base.mvvm.common.view.base;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.base.mvvm.common.utils.LogUtils;
import com.base.mvvm.common.utils.Utils;
import com.base.mvvm.common.view.dialog.CompletePrimaryDialog;
import com.base.mvvm.common.view.dialog.ConfirmPrimaryDialog;
import com.base.mvvm.common.view.dialog.ErrorDialog;
import com.base.mvvm.common.view.dialog.ProgressDialog;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerDialogFragment;
import io.reactivex.disposables.CompositeDisposable;

/**
 * BaseFragment
 */
public abstract class BaseDialogFragment<V extends ViewModel, D extends ViewDataBinding> extends DaggerDialogFragment {

  private CompositeDisposable compositeDisposable;
  protected V viewModel;
  protected D dataBinding;
  @Inject
  ViewModelProvider.Factory viewModelFactory;
  protected ProgressDialog progressDialog;
  protected ConfirmPrimaryDialog confirmPrimaryDialog;
  protected ErrorDialog errorDialog;
  protected CompletePrimaryDialog completePrimaryDialog;

  protected abstract Class<V> getViewModel();

  @LayoutRes
  protected abstract int getLayoutRes();

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    AndroidSupportInjection.inject(this);
    super.onCreate(savedInstanceState);
    compositeDisposable = new CompositeDisposable();

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());
  }

  @Override
  public void onResume() {
    super.onResume();
    LogUtils.d(getClass().getSimpleName());
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    if (dataBinding == null) {
      dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }
    return dataBinding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (progressDialog == null) {
      progressDialog = new ProgressDialog(getContext());
    }
    if (confirmPrimaryDialog == null) {
      confirmPrimaryDialog = new ConfirmPrimaryDialog(getContext());
    }
    if (errorDialog == null) {
      errorDialog = new ErrorDialog(getContext());
    }
    if (completePrimaryDialog == null) {
      completePrimaryDialog = new CompletePrimaryDialog(getContext());
      completePrimaryDialog
          .setOnKeyListener((dialog, keyCode, event) -> keyCode == KeyEvent.KEYCODE_BACK && !event.isCanceled());
    }
  }

  @Override
  public void onDestroyView() {
    if (compositeDisposable != null) {
      compositeDisposable.clear();
    }
    if (getActivity() != null) {
      Utils.hideAllKeyboard(getActivity());
    }

    super.onDestroyView();
  }

  protected void onBackPressed() {
    if (getActivity() != null) {
      getActivity().onBackPressed();
    }
  }

}
