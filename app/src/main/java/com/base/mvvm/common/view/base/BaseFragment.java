/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 10:08 AM
 *
 */

package com.base.mvvm.common.view.base;

import android.os.Bundle;
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

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;
import io.reactivex.disposables.CompositeDisposable;

/**
 * BaseFragment
 */
public abstract class BaseFragment<V extends ViewModel, D extends ViewDataBinding> extends DaggerFragment {

  private CompositeDisposable compositeDisposable;
  protected V viewModel;
  protected D dataBinding;
  @Inject
  ViewModelProvider.Factory viewModelFactory;

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