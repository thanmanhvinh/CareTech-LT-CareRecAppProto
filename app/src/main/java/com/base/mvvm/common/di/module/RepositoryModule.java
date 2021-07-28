/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/26/19 9:25 AM
 *
 */

package com.base.mvvm.common.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.base.mvvm.BuildConfig;
import com.base.mvvm.common.data.local.BaseAppDatabase;
import com.base.mvvm.common.data.local.SharedPrefsHelper;
import com.base.mvvm.common.data.remote.ApiConstants;
import com.base.mvvm.common.data.remote.AuthorizationInterceptor;
import com.base.mvvm.common.data.remote.RequestInterceptor;
import com.base.mvvm.common.di.network.NetworkHelper;
import com.base.mvvm.common.di.network.SocketHelper;
import com.base.mvvm.main.data.remote.service.HomeService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.SocketException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * all RepositoryModule for setting dagger
 */
@Module(includes = ViewModelModule.class)
public class RepositoryModule {

    private static Retrofit createRetrofit(OkHttpClient okHttpClient, Gson gson, String baseUrl) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }

    /**
     * Provides Gson.
     */
    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    /**
     * Provides Retrofit.
     */
    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return createRetrofit(okHttpClient, gson, BuildConfig.BASE_URL);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(SharedPrefsHelper appPrefs, Context context, Gson gson) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.callTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.retryOnConnectionFailure(true);
        okHttpClient.addInterceptor(new RequestInterceptor());
        okHttpClient.addInterceptor(new AuthorizationInterceptor(appPrefs));
        okHttpClient.addInterceptor(chain -> {
            Response response = null;
            try {
                response = chain.proceed(chain.request());
                if (!response.isSuccessful()) {
                    NetworkHelper.handleNetworkError(context, response, gson);
                }

            } catch (SocketException exception) {
                SocketHelper.handleSocketHelper(context);
            }
            return response;
        });
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return okHttpClient.build();
    }


    @Provides
    @Singleton
    BaseAppDatabase provideCommunicationAppDatabase(Application application) {
        return Room.databaseBuilder(application, BaseAppDatabase.class, "kinkin_app.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    SharedPrefsHelper provideSharedPrefsHelper(Context context) {
        return new SharedPrefsHelper(context);
    }


    @Provides
    @Singleton
    HomeService provideRetrofitHome(Retrofit retrofit) {
        return retrofit.create(HomeService.class);
    }
}
