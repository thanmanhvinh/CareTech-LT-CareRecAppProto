/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 10:08 AM
 *
 */

package com.base.mvvm.common.utils.exception.network;

import android.content.Context;

import com.base.mvvm.R;
import com.base.mvvm.common.utils.Utils;

import io.reactivex.Single;

public class RxNetwork {

  private RxNetwork() {
  }

  public static Single<Boolean> checkNetwork(Context context) {
    return Single.just(Utils.isNetworkConnected(context))
            .flatMap(available -> available
                    ? Single.just(true)
                    : Single.error(new NoNetworkException(context.getString(R.string.networkError))));
  }

}
