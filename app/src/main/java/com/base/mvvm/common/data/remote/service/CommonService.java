/*
 * Created by 2NF Software  on 11/29/19 9:22 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/29/19 9:22 AM
 *
 */

package com.base.mvvm.common.data.remote.service;

import com.base.mvvm.common.data.remote.response.ConfirmResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * PropertySaleService
 */
public interface CommonService {

  /**
   *
   * get categories
   */
  @GET("/event/list-category")
  Single<ConfirmResponse> getCategories(@Header("Authorization") String token);

}
