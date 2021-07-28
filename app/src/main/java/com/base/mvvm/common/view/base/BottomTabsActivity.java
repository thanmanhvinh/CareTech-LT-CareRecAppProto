/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 10:08 AM
 *
 */

package com.base.mvvm.common.view.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.base.mvvm.R;
import com.base.mvvm.common.data.local.SharedPrefsHelper;
import com.base.mvvm.common.utils.PermissionUtils;
import com.base.mvvm.common.view.dialog.CompletePrimaryDialog;
import com.base.mvvm.common.view.dialog.ConfirmPrimaryDialog;
import com.base.mvvm.common.view.dialog.ErrorDialog;
import com.base.mvvm.common.view.dialog.ProgressDialog;
import com.ncapdevi.fragnav.FragNavController;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

/**
 * BottomTabsActivity
 */
public abstract class BottomTabsActivity<V extends ViewModel, D extends ViewDataBinding> extends
    BaseActivity<D> implements BottomTabsFragment.FragmentNavigation, FragNavController.TransactionListener,
    FragNavController.RootFragmentListener {

  protected FragNavController navController = null;
  protected V viewModel;

  private ConfirmPrimaryDialog confirmPrimaryDialog;
  protected ErrorDialog errorDialog;
  private CompletePrimaryDialog completePrimaryDialog;
  protected SharedPrefsHelper sharedPrefsHelper;
  protected ProgressDialog progressDialog;
  protected PermissionUtils permissionUtils;
  private boolean isConnected ;

  @Inject
  ViewModelProvider.Factory viewModelFactory;

  protected abstract Class<V> getViewModel();

  protected abstract void onFragmentChanged(Fragment fragment, Boolean isRootFragment);

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setStatusBarGradiant(this);
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());
    if (confirmPrimaryDialog == null) {
      confirmPrimaryDialog = new ConfirmPrimaryDialog(this);
    }
    if (errorDialog == null) {
      errorDialog = new ErrorDialog(this);
    }
    if (completePrimaryDialog == null) {
      completePrimaryDialog = new CompletePrimaryDialog(this);
      completePrimaryDialog
          .setOnKeyListener((dialog, keyCode, event) -> keyCode == KeyEvent.KEYCODE_BACK && !event.isCanceled());
    }

    if (progressDialog == null) {
      progressDialog = new ProgressDialog(this);
    }

    sharedPrefsHelper = new SharedPrefsHelper(this);
    permissionUtils = new PermissionUtils(this);
  }


  @Override
  public void onBackPressed() {
    if (navController == null) {
      super.onBackPressed();
      return;
    }
    if (navController.isRootFragment()) {
      //if (!handleBackPress()) { //on a null object reference
        super.onBackPressed();
        return;
      //}
    }
    navController.popFragment();
  }

  protected abstract Boolean handleBackPress();

  @Override
  protected void onSaveInstanceState(@NotNull Bundle outState) {
    super.onSaveInstanceState(outState);
    if (navController != null) {
      navController.onSaveInstanceState(outState);
    }
  }

  @Override
  public void pushFragment(Fragment fragment) {
    if (navController != null) {
      navController.pushFragment(fragment);
    }
  }

  @Override
  public void popFragment() {
    if (navController != null) {
      navController.popFragment();
    }
  }

  @Override
  public void replaceFragment(Fragment fragment) {
    if (navController != null && fragment != navController.getCurrentFrag()) {
      navController.replaceFragment(fragment);
    }
  }

  @Override
  public void onActivityCreated(Fragment fragment) {
    if (navController != null) {
      onFragmentChanged(fragment, navController.isRootFragment());
    }
  }

  @Override
  public void onTabTransaction(@Nullable Fragment fragment, int i) {
    if (navController != null) {
      onFragmentChanged(fragment, navController.isRootFragment());
    }
  }

  @Override
  public void onFragmentTransaction(Fragment fragment, FragNavController.TransactionType transactionType) {
    if (navController != null) {
      onFragmentChanged(fragment, navController.isRootFragment());
    }
  }

  @Override
  public boolean onOptionsItemSelected(@NotNull MenuItem item) {
    if (navController != null && item.getItemId() == android.R.id.home) {
      navController.popFragment();
    }
    return true;
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public static void setStatusBarGradiant(Activity activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = activity.getWindow();
      Drawable background = activity.getResources().getDrawable(R.drawable.bg_common_title_with_menu);
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
      window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
      window.setBackgroundDrawable(background);
    }
  }
}
