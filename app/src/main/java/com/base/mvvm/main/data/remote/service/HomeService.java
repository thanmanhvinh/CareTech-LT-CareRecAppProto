/*
 * Created by 2NF Software  on 11/29/19 9:22 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/29/19 9:22 AM
 *
 */

package com.base.mvvm.main.data.remote.service;

import com.base.mvvm.main.data.remote.response.HomeResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * PropertySaleService
 */
public interface HomeService {

  /**
   * API get test info
   */
  @GET("/bin/astro.php?lon=113.2&lat=23.1&ac=0&unit=metric&output=json&tzshift=0")
  Single<HomeResponse> getDataTest();
  }
