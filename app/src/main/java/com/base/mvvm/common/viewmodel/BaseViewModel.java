/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 10:08 AM
 *
 */

package com.base.mvvm.common.viewmodel;


import android.app.Activity;

import androidx.lifecycle.ViewModel;

import com.base.mvvm.R;
import com.base.mvvm.common.utils.Constants.TypeError;
import com.base.mvvm.common.utils.SingleLiveEvent;
import com.base.mvvm.common.utils.Utils;
import com.base.mvvm.common.view.callback.IBackCallback;

import io.reactivex.disposables.CompositeDisposable;

import static com.base.mvvm.common.utils.Constants.TypeError.SERVER;

/**
 * BaseViewModel
 */
public class BaseViewModel extends ViewModel implements IBackCallback {

  private SingleLiveEvent<Boolean> isLoading;
  private SingleLiveEvent<Boolean> isError;
  private SingleLiveEvent<String> errorMsg;
  private SingleLiveEvent<Boolean> isBackFragment;
  protected String errorMessage;
  protected SingleLiveEvent<Boolean> validateError;
  private SingleLiveEvent<Boolean> isNetWork;


  protected TypeError typeError;
  protected CompositeDisposable compositeDisposable;
  private Activity activity;
  public SingleLiveEvent<Boolean> getIsLoading() {
    return isLoading;
  }

  public void setIsLoading(boolean isLoading) {
    this.isLoading.postValue(isLoading);
  }

  public SingleLiveEvent<String> getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(SingleLiveEvent<String> errorMsg) {
    this.errorMsg = errorMsg;
  }

  public SingleLiveEvent<Boolean> getIsError() {
    return isError;
  }

  public void setIsError(SingleLiveEvent<Boolean> isError) {
    this.isError = isError;
  }

  public BaseViewModel() {
    compositeDisposable = new CompositeDisposable();
    isLoading = new SingleLiveEvent<>();
    errorMsg = new SingleLiveEvent<>();
    isError = new SingleLiveEvent<>();
    isBackFragment = new SingleLiveEvent<>();
    validateError = new SingleLiveEvent<>();
    isNetWork = new SingleLiveEvent<>();

  }

  @Override
  protected void onCleared() {
    if (compositeDisposable != null) {
      compositeDisposable.clear();
    }
    super.onCleared();
  }

  public void setActivity(Activity activity) {
    this.activity = activity;
  }

  public Activity getActivity() {
    return activity;
  }

  public void hideKeyBoard(){
    if(null != activity){
      Utils.hideAllKeyboard(activity);
    }
  }

  public boolean checkNetWork(){
    if (!Utils.isNetWork(activity)) {
      typeError = SERVER;
      setIsLoading(false);
      errorMsg.postValue(activity.getString(R.string.networkError));
      return false;
    }
    return true;
  }

  public TypeError getTypeError() {
    return typeError;
  }

  public void setTypeError(TypeError typeError) {
    this.typeError = typeError;
  }

  public SingleLiveEvent<Boolean> getIsBackFragment() {
    return isBackFragment;
  }

  public void setIsBackFragment(boolean isBackFragment) {
    this.isBackFragment.postValue(isBackFragment);
  }

  @Override
  public void onClickBack() {
    this.isBackFragment.postValue(true);
  }

  @Override
  public void onClickEditEvent() {

  }

  public SingleLiveEvent<Boolean> getValidateError() {
    return validateError;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public SingleLiveEvent<Boolean> getIsNetWork() {
    return isNetWork;
  }

  public void setIsNetWork(SingleLiveEvent<Boolean> isNetWork) {
    this.isNetWork = isNetWork;
  }
}