/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/26/19 9:25 AM
 *
 */

package com.base.mvvm.common.data.remote.response;

/**
 * ConfirmResponse
 */
public class ConfirmResponse extends BaseResponse {

  private Data data;

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  public class Data {

    private String result;

    public String getResult() {
      return result;
    }

    public void setResult(String result) {
      this.result = result;
    }

  }
}
