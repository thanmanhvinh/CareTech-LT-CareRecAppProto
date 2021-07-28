/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 10:08 AM
 *
 */

package com.base.mvvm.main.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.base.mvvm.common.data.local.SharedPrefsHelper;
import com.base.mvvm.common.data.remote.response.BaseResponse;
import com.base.mvvm.common.utils.SingleLiveEvent;
import com.base.mvvm.common.utils.Utils;
import com.base.mvvm.common.viewmodel.BaseViewModel;
import com.base.mvvm.main.data.remote.response.Posts;
import com.base.mvvm.main.data.responsitory.HomeRepository;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * ActivitySplashViewModel
 */
public class DiaryViewModel extends BaseViewModel {

    private HomeRepository mRepository;
    private SharedPrefsHelper mSharedPrefsHelper;
    private SingleLiveEvent<Boolean> isNetWork;
    private String errorMessage;
    private Context context;

    @Inject
    public DiaryViewModel(HomeRepository repository, SharedPrefsHelper sharedPrefsHelper) {
        this.mRepository = repository;
        this.isNetWork = new SingleLiveEvent<>();
        this.mSharedPrefsHelper = sharedPrefsHelper;
    }

    public MutableLiveData<List<Posts>> getListPosts(Context context, int userId, String sort, String desc) {
        hideKeyBoard();
        MutableLiveData<List<Posts>> data = new MutableLiveData<>();
        if (!Utils.isNetWork(context)) {
            this.isNetWork.postValue(false);
            return data;
        }
        setIsLoading(true);
        compositeDisposable.add(
                mRepository.getPost(context)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(data::postValue, throwable -> {
                                    BaseResponse response = Utils.parserError(throwable, context);
                                    if (response != null) {
                                        errorMessage = response.getErrorMessage();
                                        validateError.postValue(true);
                                    }
                                }
                        ));
        return data;
    }


}
