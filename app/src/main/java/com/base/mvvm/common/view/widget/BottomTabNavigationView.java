/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 3:30 PM
 *
 */

package com.base.mvvm.common.view.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.IntDef;

import com.base.mvvm.R;

import java.util.ArrayList;
import java.util.List;

import icepick.Icepick;
import icepick.State;

/**
 * BottomTabNavigationView
 */
public class BottomTabNavigationView extends LinearLayout {

  public static final int TAB_HOME = 0;
  public static final int TAB_EVENTS = 1;
  public static final int TAB_CREATE_EVENT = 2;
  public static final int TAB_CALENDAR = 3;
  public static final int TAB_MY_PAGE = 4;
  public static final int NUMBER_TAB = 5;

    @State
  @TAB_INDEX
  public int selectedTabIndex = TAB_HOME;

  private final int[] TAB_CONTAINER_IDS = {R.id.buttomBarNote, R.id.bottomBarEvents, R.id.bottomBarCreateEvent,
      R.id.bottomBarCalendar, R.id.bottomBarMyPage};
  private List<BottomTabView> tabs;
  private OnTabSelectedListener onTabSelectedListener = null;
  private boolean isReselect;

  public BottomTabNavigationView(Context context) {
    super(context);
    init(context);
  }

  public BottomTabNavigationView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public BottomTabNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  @SuppressLint("NonConstantResourceId")
  private void init(Context context) {
    setOrientation(HORIZONTAL);
    View view = inflate(context, R.layout.view_bottom_tab_navigation, this);
    tabs = new ArrayList<>();
    for (int tabContainerId : TAB_CONTAINER_IDS) {
      BottomTabView bottomTabView = view.findViewById(tabContainerId);
      tabs.add(bottomTabView);
    }

    for (BottomTabView tab : tabs) {
      tab.setOnClickListener(v -> {
        int tabIndex;
        switch (tab.getId()) {
          case R.id.buttomBarNote:
            isReselect = true;
            tabIndex = TAB_HOME;
            break;
          case R.id.bottomBarEvents:
            isReselect = true;
            tabIndex = TAB_EVENTS;
            break;
          case R.id.bottomBarCreateEvent:
            isReselect = true;
            tabIndex = TAB_CREATE_EVENT;
            break;
          case R.id.bottomBarCalendar:
            isReselect = true;
            tabIndex = TAB_CALENDAR;
            break;
          case R.id.bottomBarMyPage:
            isReselect = true;
            tabIndex = TAB_MY_PAGE;
            break;
          default:
            tabIndex = TAB_HOME;

        }
        selectTab(tabIndex);
      });
    }
    selectTab(TAB_HOME);
  }

  public void selectTab(@TAB_INDEX int tabIndex) {
    selectedTabIndex = tabIndex;

    if (isReselect) {
      if (tabs.get(tabIndex).isSelected()) {
        if (onTabSelectedListener != null) {
          onTabSelectedListener.onTabReselected(tabIndex);
        }
        return;
      }
    }
    if (onTabSelectedListener != null && !onTabSelectedListener.onTabSelected(tabIndex)) {
      return;
    }

    for (int i = 0; i < tabs.size(); i++) {
      tabs.get(i).setSelected(i == tabIndex);
    }
  }

  @Override
  protected Parcelable onSaveInstanceState() {
    return Icepick.saveInstanceState(this, super.onSaveInstanceState());
  }

  @Override
  protected void onRestoreInstanceState(Parcelable state) {
    super.onRestoreInstanceState(Icepick.restoreInstanceState(this, state));
    if (!tabs.get(selectedTabIndex).isSelected()) {
      selectTab(selectedTabIndex);
    }
  }

  public int size() {
    return tabs.size();
  }

  public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
    this.onTabSelectedListener = onTabSelectedListener;
  }

  public void setCountNotification(int count) {
    tabs.get(TAB_CALENDAR).showBadgeNotification(count);
  }

  public List<BottomTabView> getTabs() {
    return tabs;
  }

  public void setActivity(Activity activity) {
  }

  public interface OnTabSelectedListener {

    Boolean onTabSelected(@TAB_INDEX int tabIndex);

    void onTabReselected(@TAB_INDEX int tabIndex);


  }

  @IntDef({TAB_HOME, TAB_EVENTS, TAB_CREATE_EVENT, TAB_CALENDAR, TAB_MY_PAGE})
  @interface TAB_INDEX {

  }
}
