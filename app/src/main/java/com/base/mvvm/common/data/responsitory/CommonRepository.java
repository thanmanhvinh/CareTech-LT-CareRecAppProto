/*
 * Created by 2NF Software  on 11/29/19 9:22 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/29/19 9:22 AM
 *
 */

package com.base.mvvm.common.data.responsitory;

import android.content.Context;

import com.base.mvvm.common.data.remote.response.ConfirmResponse;
import com.base.mvvm.common.data.remote.service.CommonService;
import com.base.mvvm.common.utils.exception.network.RxNetwork;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * ViewModelに対するデータプロバイダ レスポンスをLiveData Objectにラップする
 */
public class CommonRepository {

  private CommonService service;

  @Inject
  public CommonRepository(CommonService service) {
    this.service = service;
  }

  /**
   * @param context
   * @param token
   * @return
   */
  public Single<ConfirmResponse> getCategories(Context context, String token) {
    return RxNetwork.checkNetwork(context)
            .flatMap(connected -> service.getCategories(token))
            .subscribeOn(Schedulers.io());
  }


}


