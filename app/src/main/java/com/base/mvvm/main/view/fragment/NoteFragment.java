package com.base.mvvm.main.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.mvvm.R;
import com.base.mvvm.common.utils.LogUtils;
import com.base.mvvm.common.utils.ToastUtils;
import com.base.mvvm.common.view.base.BottomTabsFragment;
import com.base.mvvm.databinding.FragmentNoteBinding;
import com.base.mvvm.main.viewmodel.NoteViewModel;

public class NoteFragment extends BottomTabsFragment<NoteViewModel, FragmentNoteBinding> {
    public static final String TAG = NoteFragment.class.getSimpleName();


    public static NoteFragment newsInstance() {
        Bundle args = new Bundle();
        NoteFragment fragment = new NoteFragment();
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    protected void onViewInitialized(View view, Bundle savedInstanceState, Boolean isViewCreated) {
    }

    @Override
    protected Class<NoteViewModel> getViewModel() {
        return NoteViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_note;
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

    private void observeViewModel(NoteViewModel viewModel) {

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

        viewModel.getActivityInfo(getContext()).observe(this,homeResponse -> {
            viewModel.setIsLoading(false);
            if(!homeResponse.getProduct().isEmpty()) {
                LogUtils.d("Home " + homeResponse.getProduct());
                ToastUtils.showToast(getContext(), "Data test " + homeResponse.getProduct());
            }else {
                errorDialog.show();
            }
        });
    }
}
