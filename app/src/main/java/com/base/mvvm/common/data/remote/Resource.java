/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/26/19 9:25 AM
 *
 */

package com.base.mvvm.common.data.remote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import static com.base.mvvm.common.data.remote.Status.ERROR;
import static com.base.mvvm.common.data.remote.Status.LOADING;
import static com.base.mvvm.common.data.remote.Status.SUCCESS;
/**
 * Resource
 * @param <T>
 */
public class Resource<T> {

  @NonNull
  public Status status;
  @Nullable
  public T data;
  @Nullable
  public String message;

  private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
    this.status = status;
    this.data = data;
    this.message = message;
  }

  public static <T> Resource<T> success(@NonNull T data) {
    return new Resource<>(SUCCESS, data, null);
  }

  public static <T> Resource<T> error(String msg, @Nullable T data) {
    return new Resource<>(ERROR, data, msg);
  }

  public static <T> Resource<T> loading(@Nullable T data) {
    return new Resource<>(LOADING, data, null);
  }

  @NonNull
  public Status getStatus() {
    return status;
  }

  public void setStatus(@NonNull Status status) {
    this.status = status;
  }

  @Nullable
  public T getData() {
    return data;
  }

  public void setData(@Nullable T data) {
    this.data = data;
  }

  @Nullable
  public String getMessage() {
    return message;
  }

  public void setMessage(@Nullable String message) {
    this.message = message;
  }
}