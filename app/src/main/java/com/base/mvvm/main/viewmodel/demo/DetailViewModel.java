package com.base.mvvm.main.viewmodel.demo;

import android.content.Context;

import com.base.mvvm.common.data.local.SharedPrefsHelper;
import com.base.mvvm.common.utils.SingleLiveEvent;
import com.base.mvvm.common.viewmodel.BaseViewModel;
import com.base.mvvm.main.data.responsitory.HomeRepository;

import javax.inject.Inject;

public class DetailViewModel extends BaseViewModel {

    private HomeRepository mRepository;
    private SharedPrefsHelper mSharedPrefsHelper;
    private SingleLiveEvent<Boolean> isNetWork;
    private String errorMessage;

    @Inject
    public DetailViewModel(HomeRepository repository, SharedPrefsHelper sharedPrefsHelper) {
        this.mRepository = repository;
        this.isNetWork = new SingleLiveEvent<>();
        this.mSharedPrefsHelper = sharedPrefsHelper;
    }

}
