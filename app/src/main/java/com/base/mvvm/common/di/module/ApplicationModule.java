/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/26/19 9:25 AM
 *
 */

package com.base.mvvm.common.di.module;

import android.content.Context;

import com.base.mvvm.KinKinApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * ApplicationModule for setting dagger
 */
@Module
public class ApplicationModule {

  private KinKinApplication application;

  public ApplicationModule(KinKinApplication application) {
    this.application = application;
  }

  @Provides
  @Singleton
  KinKinApplication provideApplication() {
    return application;
  }

  @Provides
  @Singleton
  Context provideContext() {
    return application.getApplicationContext();
  }
}
