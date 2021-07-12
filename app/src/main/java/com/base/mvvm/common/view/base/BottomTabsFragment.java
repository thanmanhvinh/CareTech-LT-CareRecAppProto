/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 10:08 AM
 *
 */

package com.base.mvvm.common.view.base;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.base.mvvm.common.data.local.SharedPrefsHelper;
import com.base.mvvm.common.view.dialog.CompletePrimaryDialog;
import com.base.mvvm.common.view.dialog.ConfirmPrimaryDialog;
import com.base.mvvm.common.view.dialog.ErrorDialog;
import com.base.mvvm.common.view.dialog.ProgressDialog;
import com.base.mvvm.common.view.widget.SwipeRefreshHelper;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * BottomTabsFragment
 */
public abstract class BottomTabsFragment<V extends ViewModel, D extends ViewDataBinding> extends BaseFragment<V, D> {
  protected ConfirmPrimaryDialog confirmPrimaryDialog;
  protected ErrorDialog errorDialog;
  protected CompletePrimaryDialog completePrimaryDialog;
  protected SharedPrefsHelper sharedPrefsHelper;
  protected ProgressDialog progressDialog;
  private FragmentNavigation fragmentNavigation = null;
  private boolean isViewCreated = false;
  protected SwipeRefreshHelper swipeRefreshHelper;

  @Inject
  protected Gson gson;
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof FragmentNavigation) {
      fragmentNavigation = (FragmentNavigation) context;
    }
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (fragmentNavigation != null) {
      fragmentNavigation.onActivityCreated(this);
    }

  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
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

    if (getContext() != null) {
      if (progressDialog == null) {
        progressDialog = new ProgressDialog(getContext());
      }

      sharedPrefsHelper = new SharedPrefsHelper(getContext());
    }

    onViewInitialized(view, savedInstanceState, isViewCreated);
    if (!isViewCreated) {
      isViewCreated = true;
    }
  }

  @Nullable
  @Override
  public View getView() {
    View view = super.getView();
    return view != null ? view : dataBinding.getRoot();
  }

  protected void push(Fragment fragment) {
    if (fragmentNavigation != null) {
      fragmentNavigation.pushFragment(fragment);
    }
  }

  protected void pop() {
    if (fragmentNavigation != null) {
      fragmentNavigation.popFragment();
    }
  }

  public void replace(Fragment fragment) {
    if (fragmentNavigation != null) {
      fragmentNavigation.replaceFragment(fragment);
    }
  }


  protected void finish() {
    pop();
  }

  // protected abstract View onInitializeView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

  protected abstract void onViewInitialized(View view, Bundle savedInstanceState, Boolean
          isViewCreated);

  public interface FragmentNavigation {

    void onActivityCreated(Fragment fragment);

    void pushFragment(Fragment fragment);

    void popFragment();

    void replaceFragment(Fragment fragment);
  }
}
