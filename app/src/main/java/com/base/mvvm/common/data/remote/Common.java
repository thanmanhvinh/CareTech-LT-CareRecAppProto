/*
 * Created by 2NF Software  on 1/13/20 1:23 PM
 * Copyright© 2020 IGNITIONPOINT Inc. All Rights Reserved.
 * Last modified 1/13/20 1:23 PM
 *
 */

package com.base.mvvm.common.data.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Create by IGNITIONPOINT Inc on 2020-01-13.
 */
public class Common {

  @SerializedName("latest_notice_timestamp")
  private String latestNoticeTimeStamp;

  public String getLatestNoticeTimeStamp() {
    return latestNoticeTimeStamp;
  }

  public void setLatestNoticeTimeStamp(String latestNoticeTimeStamp) {
    this.latestNoticeTimeStamp = latestNoticeTimeStamp;
  }
}
