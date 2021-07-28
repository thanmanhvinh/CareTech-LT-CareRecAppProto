/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/26/19 9:25 AM
 *
 */

package com.base.mvvm.common.di.components;

import android.app.Application;

import com.base.mvvm.KinKinApplication;
import com.base.mvvm.common.di.builder.ActivityBuilderModule;
import com.base.mvvm.common.di.module.ApplicationModule;
import com.base.mvvm.common.di.module.FragmentModule;
import com.base.mvvm.common.di.module.RepositoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
    RepositoryModule.class,
    FragmentModule.class,
    AndroidInjectionModule.class,
    ActivityBuilderModule.class,
    ApplicationModule.class
})
public interface AppComponent extends AndroidInjector<KinKinApplication> {

  void inject(KinKinApplication communicationGroupApp);

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder application(Application application);

    Builder applicationModule(ApplicationModule applicationModule);

    AppComponent build();
  }
}