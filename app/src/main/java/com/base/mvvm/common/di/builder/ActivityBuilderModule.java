/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/26/19 9:25 AM
 *
 */

package com.base.mvvm.common.di.builder;



import com.base.mvvm.main.view.activity.MainActivity;
import com.base.mvvm.main.view.activity.web_view.WebViewActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * ActivityBuilderModule for setting dagger
 */
@Module
public abstract class ActivityBuilderModule {

  @SuppressWarnings("unused")
  @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
  abstract MainActivity mainActivity();

  @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
  abstract WebViewActivity webViewActivity();


}
