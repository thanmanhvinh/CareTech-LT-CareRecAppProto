package com.base.mvvm.main.view.fragment.demo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.mvvm.R;
import com.base.mvvm.common.view.base.BaseFragment;
import com.base.mvvm.common.view.widget.BottomTabNavigationView;
import com.base.mvvm.databinding.FragmentDetailBinding;
import com.base.mvvm.main.viewmodel.demo.DetailViewModel;


public class DetailFragment extends BaseFragment<DetailViewModel, FragmentDetailBinding> {

    public static final String TAG = DetailFragment.class.getSimpleName();

    private BottomTabNavigationView navBar;

    public static DetailFragment newsInstance() {
        Bundle args = new Bundle();
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<DetailViewModel> getViewModel() {
        return DetailViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_detail;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navBar = getActivity().findViewById(R.id.bottomBar);
        navBar.setVisibility(View.GONE);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        navBar.setVisibility(View.VISIBLE);
    }
}