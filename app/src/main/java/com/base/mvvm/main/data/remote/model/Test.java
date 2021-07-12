/*
 * Created by IGNITIONPOINT Inc on 11/26/19 9:35 AM
 * Copyright© 2019 IGNITIONPOINT Inc. All Rights Reserved.
 * Last modified 11/20/19 11:13 AM
 *
 */

package com.base.mvvm.main.data.remote.model;

import com.google.gson.annotations.SerializedName;

/**
 * Area
 */
public class Test {

  @SerializedName("activity")
  private String activityInfo;

  @SerializedName("charity")
  private String charity;

  @SerializedName("price")
  private float price;

  public String getActivityInfo() {
    return activityInfo;
  }

  public void setActivityInfo(String activity) {
    this.activityInfo = activity;
  }

  public String getCharity() {
    return charity;
  }

  public void setCharity(String charity) {
    this.charity = charity;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }
}
