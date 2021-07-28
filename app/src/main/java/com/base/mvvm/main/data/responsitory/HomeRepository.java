/*
 * Created by 2NF Software  on 11/29/19 9:22 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/29/19 9:22 AM
 *
 */

package com.base.mvvm.main.data.responsitory;

import android.content.Context;

import com.base.mvvm.common.utils.exception.network.RxNetwork;
import com.base.mvvm.main.data.remote.model.movies.Movie;
import com.base.mvvm.main.data.remote.model.movies.MovieResponse;
import com.base.mvvm.main.data.remote.response.HomeResponse;
import com.base.mvvm.main.data.remote.response.Posts;
import com.base.mvvm.main.data.remote.service.HomeService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * ViewModelに対するデータプロバイダ レスポンスをLiveData Objectにラップする
 */
public class HomeRepository {

  private HomeService service;
  private Context context;

  @Inject
  public HomeRepository(HomeService service) {
    this.service = service;
  }


  /**
   * Get activity info
   */

  public Single<HomeResponse> getActivityInfo(Context context) {
    return RxNetwork.checkNetwork(context)
        .flatMap(connected -> service.getDataTest())
        .subscribeOn(Schedulers.io());
  }

  public Single<List<Posts>> getPost(Context context) {
    return RxNetwork.checkNetwork(context)
            .flatMap(connected -> service.getPosts())
            .subscribeOn(Schedulers.io());
  }

  public Single<Movie> getListMovie(Context context, String api_key) {
    return RxNetwork.checkNetwork(context)
            .flatMap(connected -> service.getListMovie(api_key))
            .subscribeOn(Schedulers.io());
  }

}


