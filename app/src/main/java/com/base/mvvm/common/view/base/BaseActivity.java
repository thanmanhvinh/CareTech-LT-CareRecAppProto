/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 10:08 AM
 *
 */

package com.base.mvvm.common.view.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.base.mvvm.common.data.local.SharedPrefsHelper;
import com.base.mvvm.common.utils.LogUtils;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.disposables.CompositeDisposable;

/**
 * BaseActivity
 */
public abstract class BaseActivity<D extends ViewDataBinding> extends AppCompatActivity implements
        HasSupportFragmentInjector {

  @SuppressWarnings("unused")
  public D dataBinding;

  protected CompositeDisposable compositeDisposable;
  @Inject
  protected SharedPrefsHelper appPrefs;
  @Inject
  DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;

  @LayoutRes
  protected abstract int getLayoutRes();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    dataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
    compositeDisposable = new CompositeDisposable();
  }

  @Override
  protected void onResume() {
    super.onResume();
    LogUtils.d(getClass().getSimpleName());
  }

  @Override
  protected void onDestroy() {
    if (compositeDisposable != null) {
      compositeDisposable.clear();
    }
    super.onDestroy();
  }

  @Override
  public void onBackPressed() {
    if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
      getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }else {
      super.onBackPressed();
    }
  }

  @Override
  public AndroidInjector<Fragment> supportFragmentInjector() {
    return fragmentAndroidInjector;
  }
}


