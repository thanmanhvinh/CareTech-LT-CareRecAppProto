/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/26/19 9:25 AM
 *
 */

package com.base.mvvm.common.data.local.event;

import androidx.annotation.IntDef;

/**
 * Event in screen
 */
public class AccountEvent {
  public static final int LOGIN = 1,CREATE_EVENT = 2;
  public int targetPage;
  public Object object;

  public AccountEvent(@ACCOUNT_TAB_INDEX int targetPage) {
    this.targetPage = targetPage;
  }

  @IntDef({ LOGIN,CREATE_EVENT})
  @interface ACCOUNT_TAB_INDEX {

  }
}