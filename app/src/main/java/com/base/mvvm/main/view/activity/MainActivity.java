package com.base.mvvm.main.view.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.base.mvvm.main.view.fragment.DiaryFragment;
import com.base.mvvm.main.view.fragment.ListUserFragment;
import com.base.mvvm.main.view.fragment.HistoryFragment;
import com.base.mvvm.main.view.fragment.NoteFragment;
import com.base.mvvm.R;
import com.base.mvvm.main.view.fragment.ReportFragment;
import com.base.mvvm.common.utils.LogUtils;
import com.base.mvvm.common.view.base.BottomTabsActivity;
import com.base.mvvm.common.view.widget.BottomTabNavigationView;
import com.base.mvvm.databinding.ActivityMainBinding;
import com.base.mvvm.main.viewmodel.ActivityMainViewModel;
import com.ncapdevi.fragnav.FragNavController;
import com.ncapdevi.fragnav.tabhistory.FragNavTabHistoryController;

public class MainActivity extends BottomTabsActivity<ActivityMainViewModel, ActivityMainBinding> implements BottomTabNavigationView.OnTabSelectedListener{

    public static final String TAG = MainActivity.class.getSimpleName();
    //fragment inscreen
    private DiaryFragment secondFragment;

    private NoteFragment firstFragment;
    private HistoryFragment thirdFragment;
    private ReportFragment fourthFragment;
    private ListUserFragment fifthFragment;


    @Override
    protected Class<ActivityMainViewModel> getViewModel() {
        return ActivityMainViewModel.class;
    }

    @Override
    protected void onFragmentChanged(Fragment fragment, Boolean isRootFragment) {

    }

    @Override
    protected int getLayoutRes() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragments();
        initNav(savedInstanceState);
    }

    /**
     * Init all fragment in menu
     **/
    private void initFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        firstFragment = firstFragment != null ? (NoteFragment) fragmentManager.findFragmentByTag(NoteFragment.TAG) : NoteFragment.newsInstance();
        secondFragment = secondFragment != null ? (DiaryFragment) fragmentManager.findFragmentByTag(DiaryFragment.TAG) : DiaryFragment.newsInstance();
        thirdFragment = thirdFragment != null ? (HistoryFragment) fragmentManager.findFragmentByTag(HistoryFragment.TAG) : HistoryFragment.newsInstance();
        fourthFragment = fourthFragment != null ? (ReportFragment) fragmentManager.findFragmentByTag(ReportFragment.TAG) : ReportFragment.newsInstance();
        fifthFragment = fifthFragment != null ? (ListUserFragment) fragmentManager.findFragmentByTag(ListUserFragment.TAG) : ListUserFragment.newsInstance();
    }

    /**
     * Init menu
     **/
    private void initNav(Bundle savedInstanceState) {
        dataBinding.bottomBar.setOnTabSelectedListener(this);
        int index = 0;
        navController = FragNavController
                .newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.fragment_container)
                .transactionListener(this)
                .rootFragmentListener(this, BottomTabNavigationView.NUMBER_TAB)
                .popStrategy(FragNavTabHistoryController.UNIQUE_TAB_HISTORY)
                .switchController((i, fragNavTransactionOptions) -> {
                })
                .selectedTabIndex(index)
                .build();
    }
    @Override
    protected Boolean handleBackPress() {
        return null;
    }

    @Override
    public Fragment getRootFragment(int index) {
        switch (index) {
            case BottomTabNavigationView.TAB_HOME:
                return firstFragment;
            case BottomTabNavigationView.TAB_EVENTS:
                return secondFragment;
            case BottomTabNavigationView.TAB_CREATE_EVENT:
                return thirdFragment;
            case BottomTabNavigationView.TAB_CALENDAR:
                return fourthFragment;
            case BottomTabNavigationView.TAB_MY_PAGE:
                return fifthFragment;
        }

        LogUtils.d(index + "");
        throw new RuntimeException("invalid position " + index);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public Boolean onTabSelected(int tabIndex) {
        if (navController != null) {
            navController.switchTab(tabIndex);
        }
        return true;
    }

    @Override
    public void onTabReselected(int tabIndex) {
        LogUtils.d(tabIndex + " tabIndex");
        if (navController != null) {
            switch (tabIndex) {
                case BottomTabNavigationView.TAB_HOME:
                    navController.replaceFragment(NoteFragment.newsInstance());
                    navController.clearStack();
                    break;
                case BottomTabNavigationView.TAB_EVENTS:
                    navController.replaceFragment(DiaryFragment.newsInstance());
                    navController.clearStack();
                    break;
                case BottomTabNavigationView.TAB_CREATE_EVENT:
                    navController.replaceFragment(HistoryFragment.newsInstance());
                    navController.clearStack();
                    break;
                case BottomTabNavigationView.TAB_CALENDAR:
                    navController.replaceFragment(ReportFragment.newsInstance());
                    navController.clearStack();
                    break;
                case BottomTabNavigationView.TAB_MY_PAGE:
                    navController.replaceFragment(ListUserFragment.newsInstance());
                    navController.clearStack();
                    break;
                default:
                    navController.replaceFragment(DiaryFragment.newsInstance());
                    navController.clearStack();
                    break;
            }
        }
    }
}