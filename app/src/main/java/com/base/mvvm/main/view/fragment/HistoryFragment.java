package com.base.mvvm.main.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.base.mvvm.R;
import com.base.mvvm.common.view.base.BottomTabsFragment;
import com.base.mvvm.databinding.FragmentHistoryBinding;
import com.base.mvvm.main.viewmodel.HistoryViewModel;

public class HistoryFragment extends BottomTabsFragment<HistoryViewModel, FragmentHistoryBinding> {
    public static final String TAG = HistoryFragment.class.getSimpleName();

    public static HistoryFragment newsInstance() {
        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onViewInitialized(View view, Bundle savedInstanceState, Boolean isViewCreated) {
    }

    @Override
    protected Class<HistoryViewModel> getViewModel() {
        return HistoryViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_history;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        viewModel.setActivity(getActivity());
        return dataBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        observeViewModel(viewModel);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void observeViewModel(HistoryViewModel viewModel) {

        dataBinding.buttonFirst.setOnClickListener(v -> {
            Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
        });

        viewModel.getIsNetWork().observe(this, flag -> {
            if (!flag) {
                progressDialog.dismiss();

            }
        });


        viewModel.getIsBackFragment().observe(this, flag -> {
            if (flag) {
                onBackPressed();
            }
        });

        viewModel.getValidateError().observe(this, flag -> {
            if (flag) {
                progressDialog.dismiss();
                viewModel.getValidateError().postValue(false);
                errorDialog.setMessage(viewModel.getErrorMessage());
                errorDialog.show();

            }
        });

        viewModel.getIsLoading().observe(this, loading -> {
            if (!loading) {
                progressDialog.dismiss();
            }else {
                if(!progressDialog.isShowing()){
                    progressDialog.show();
                }

            }

        });


    }
}
