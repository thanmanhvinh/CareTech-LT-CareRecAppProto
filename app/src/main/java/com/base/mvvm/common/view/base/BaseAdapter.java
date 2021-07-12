/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 10:08 AM
 *
 */

package com.base.mvvm.common.view.base;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * BaseAdapter
 * @param <T>
 * @param <D>
 */
public abstract class BaseAdapter<T extends RecyclerView.ViewHolder, D> extends RecyclerView.Adapter<T> {

  public abstract void setData(List<D> data);
}