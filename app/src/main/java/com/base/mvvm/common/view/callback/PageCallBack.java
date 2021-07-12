/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/20/19 11:13 AM
 *
 */

package com.base.mvvm.common.view.callback;

/**
 * Common page call back
 */
public interface PageCallBack {

  /**
   * onClick next page
   */
  void onClickActionNext(String page);

  /**
   * on click back page
   */
  void onClickActionPrevious(String page);
}
