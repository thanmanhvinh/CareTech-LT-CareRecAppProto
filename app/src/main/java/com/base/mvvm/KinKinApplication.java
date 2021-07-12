/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 10:11 AM
 *
 */

package com.base.mvvm;

import com.base.mvvm.common.di.components.DaggerAppComponent;
import com.base.mvvm.common.di.module.ApplicationModule;
import com.base.mvvm.common.utils.LogUtils;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * ShinokenApp
 */
public class KinKinApplication extends DaggerApplication {

    private static KinKinApplication sInstance;


    public static KinKinApplication  getAppContext() {
        return sInstance;
    }

    private static synchronized void setInstance(KinKinApplication app) {
        sInstance = app;
    }



    @Override
    public void onCreate() {
        super.onCreate();

        LogUtils.init();

        RxJavaPlugins.setErrorHandler(throwable -> {
            if (throwable instanceof UndeliverableException) {
                return; // ignore BleExceptions as they were surely delivered at least once
            }
            // add other custom handlers if needed
            throw new RuntimeException("Unexpected Throwable in RxJavaPlugins error handler", throwable);
        });


        setInstance(this);


    }


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .application(this)
                .applicationModule(new ApplicationModule(this))
                .build();
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
