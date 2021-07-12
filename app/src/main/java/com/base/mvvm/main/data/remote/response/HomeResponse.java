/*
 * Created by 2NF Software  on 11/29/19 9:22 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/29/19 9:22 AM
 *
 */

package com.base.mvvm.main.data.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * AreasResponse
 */
public class HomeResponse {

  @SerializedName("product")
  private String product;
  @SerializedName("init")
  private String init;

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public String getInit() {
    return init;
  }

  public void setInit(String init) {
    this.init = init;
  }
}
