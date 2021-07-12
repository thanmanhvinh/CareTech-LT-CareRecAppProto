package com.base.mvvm.main.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.mvvm.R;
import com.base.mvvm.common.view.base.BottomTabsFragment;
import com.base.mvvm.databinding.FragmentDiaryBinding;
import com.base.mvvm.main.viewmodel.DiaryViewModel;

public class DiaryFragment extends BottomTabsFragment<DiaryViewModel, FragmentDiaryBinding> {
    public static final String TAG = NoteFragment.class.getSimpleName();

    public static DiaryFragment newsInstance() {
        Bundle args = new Bundle();
        DiaryFragment fragment = new DiaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onViewInitialized(View view, Bundle savedInstanceState, Boolean isViewCreated) {
    }

    @Override
    protected Class<DiaryViewModel> getViewModel() {
        return DiaryViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_diary;
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

    private void observeViewModel(DiaryViewModel viewModel) {

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
