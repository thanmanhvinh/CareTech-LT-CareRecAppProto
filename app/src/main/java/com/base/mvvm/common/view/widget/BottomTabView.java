/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 10:08 AM
 *
 */

package com.base.mvvm.common.view.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.base.mvvm.R;

/**
 * BottomTabView
 */
public class BottomTabView extends LinearLayout {

  private AppCompatTextView badgeNotification;

  public BottomTabView(Context context) {
    super(context);
    init(context, null, 0);
  }

  public BottomTabView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs, 0);
  }

  public BottomTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs, defStyleAttr);
  }

  private void init(Context context, AttributeSet attrs, int defStyleAttr) {
    View view = inflate(context, R.layout.view_bottom_tab, this);
    setOrientation(VERTICAL);
    setGravity(Gravity.CENTER_VERTICAL);
    TypedArray type = context.getTheme().obtainStyledAttributes(attrs,
        R.styleable.BottomTabView,
        defStyleAttr,
        0);
    int icon = type.getResourceId(R.styleable.BottomTabView_icon_drawable, 0);
    String title = type.getString(R.styleable.BottomTabView_tab_name);
    ColorStateList textColor = type.getColorStateList(R.styleable.BottomTabView_tab_text_color);

    AppCompatImageView tabIcon = view.findViewById(R.id.tabIcon);
    AppCompatTextView tabTitle = view.findViewById(R.id.tabTitle);
    tabIcon.setImageResource(icon);
    badgeNotification = view.findViewById(R.id.badgeNotification);
    tabTitle.setText(title);
    tabTitle.setTextColor(textColor);
  }

  public void showBadgeNotification(int count) {
    if (badgeNotification == null) {
      return;
    }
    if (count > 0) {
      badgeNotification.setVisibility(VISIBLE);
//      badgeNotification.setText(String.valueOf(count));
    } else {
      badgeNotification.setVisibility(GONE);
    }
  }
}
