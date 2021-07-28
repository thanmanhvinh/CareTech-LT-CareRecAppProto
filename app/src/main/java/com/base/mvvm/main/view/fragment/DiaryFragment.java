package com.base.mvvm.main.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.base.mvvm.R;
import com.base.mvvm.common.utils.LogUtils;
import com.base.mvvm.common.view.base.BottomTabsFragment;
import com.base.mvvm.databinding.FragmentDiaryBinding;
import com.base.mvvm.main.data.remote.response.Posts;
import com.base.mvvm.main.view.fragment.adapter.ListPostAdapter;
import com.base.mvvm.main.view.fragment.adapter.MovieAdapter;
import com.base.mvvm.main.viewmodel.DiaryViewModel;

import java.util.ArrayList;
import java.util.List;

public class DiaryFragment extends BottomTabsFragment<DiaryViewModel, FragmentDiaryBinding> implements ListPostAdapter.SetOnClickItem, MovieAdapter.SetOnClickItem{
    public static final String TAG = NoteFragment.class.getSimpleName();

    private List<Posts> mList;
    private ListPostAdapter mListPostAdapter;

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
                //errorDialog.show();

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

        //
        viewModel.getListPosts(getContext(), 3, "sort", "desc").observe(this, response -> {
            viewModel.setIsLoading(false);
            if(response != null) {
                LogUtils.d("Home " + response.get(1).getTitle());
                Log.d("123123", "titles: "+response.get(1).getTitle() + "\n " + "body "+response.get(1).getBody());
                //ToastUtils.showToast(getContext(), "Data test " + homeResponse.getProduct());

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                dataBinding.rcyListPosts.setLayoutManager(linearLayoutManager);
                mList = new ArrayList<>();
                mListPostAdapter = new ListPostAdapter(mList, this);
                mListPostAdapter.updateList(response);
                dataBinding.rcyListPosts.setAdapter(mListPostAdapter);

            }else {
                errorDialog.show();
            }
        });


    }

    @Override
    public void OnclickItem(int position) {
        Toast.makeText(getContext(), "click "+ position, Toast.LENGTH_SHORT).show();
    }
}
