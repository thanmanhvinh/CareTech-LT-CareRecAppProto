package com.base.mvvm.main.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.base.mvvm.common.data.local.SharedPrefsHelper;
import com.base.mvvm.common.data.remote.response.BaseResponse;
import com.base.mvvm.common.utils.SingleLiveEvent;
import com.base.mvvm.common.utils.Utils;
import com.base.mvvm.common.viewmodel.BaseViewModel;
import com.base.mvvm.main.data.remote.response.HomeResponse;
import com.base.mvvm.main.data.responsitory.HomeRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ReportViewModel extends BaseViewModel {

    private HomeRepository mRepository;
    private SharedPrefsHelper mSharedPrefsHelper;
    private SingleLiveEvent<Boolean> isNetWork;
    private String errorMessage;

    @Inject
    public ReportViewModel(HomeRepository repository, SharedPrefsHelper sharedPrefsHelper) {
        this.mRepository = repository;
        this.isNetWork = new SingleLiveEvent<>();
        this.mSharedPrefsHelper = sharedPrefsHelper;
    }

    public MutableLiveData<HomeResponse> getActivityInfo(Context context) {
        hideKeyBoard();
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        if (!Utils.isNetWork(context)) {
            this.isNetWork.postValue(false);
            return data;
        }
        setIsLoading(true);
        compositeDisposable.add(
                mRepository.getActivityInfo(context)
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
