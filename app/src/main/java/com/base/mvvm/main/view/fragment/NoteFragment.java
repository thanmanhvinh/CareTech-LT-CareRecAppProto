package com.base.mvvm.main.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.base.mvvm.R;
import com.base.mvvm.common.utils.LogUtils;
import com.base.mvvm.common.utils.ToastUtils;
import com.base.mvvm.common.view.base.BottomTabsFragment;
import com.base.mvvm.databinding.FragmentNoteBinding;
import com.base.mvvm.main.data.remote.model.movies.MovieResponse;
import com.base.mvvm.main.view.fragment.adapter.ListPostAdapter;
import com.base.mvvm.main.view.fragment.adapter.MovieAdapter;
import com.base.mvvm.main.view.fragment.demo.DetailFragment;
import com.base.mvvm.main.viewmodel.NoteViewModel;
import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends BottomTabsFragment<NoteViewModel, FragmentNoteBinding> implements MovieAdapter.SetOnClickItem, ListPostAdapter.SetOnClickItem{
    public static final String TAG = NoteFragment.class.getSimpleName();

    private List<MovieResponse> mListMovie;
    private MovieAdapter mMovieAdapter;
    private FragmentTransaction transaction;

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
        dataBinding.textView5.setOnClickListener(v -> {
            //NavHostFragment.findNavController(DetailFragment.newsInstance());
            transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new DetailFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }

    @SuppressLint("LogNotTimber")
    private void observeViewModel(NoteViewModel viewModel) {

        /*dataBinding.textView5.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_FirstFragment_to_detailFragment);
        });*/

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

        viewModel.getActivityInfo(getContext()).observe(getViewLifecycleOwner(),homeResponse -> {
            viewModel.setIsLoading(false);
            if(!homeResponse.getProduct().isEmpty()) {
                LogUtils.d("Home " + homeResponse.getProduct());
                ToastUtils.showToast(getContext(), "Data test " + homeResponse.getProduct());
            }else {
                errorDialog.show();
            }
        });

        viewModel.getListMovie(getContext(), "034bbd1b233d6726e0c7dc7f338657f9").observe(this, response -> {
            viewModel.setIsLoading(false);
            if(response != null) {
                //LogUtils.d("Home " + response.get(1).getTitle());
                Log.d("123123", "titles: "+response.getResults().get(0).getTitle());
                Log.d("123123", "movie_id: "+response.getResults().get(0).getId());

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                /*DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(dataBinding.rcyReport.getContext(), linearLayoutManager.getOrientation());
                dataBinding.rcyReport.addItemDecoration(dividerItemDecoration);*/
                dataBinding.rcyReport.setLayoutManager(linearLayoutManager);

                mListMovie = new ArrayList<>();
                mMovieAdapter = new MovieAdapter(getContext(), mListMovie, this);
                mMovieAdapter.updateList(response.getResults());
                dataBinding.rcyReport.setAdapter(mMovieAdapter);

            }else {
                errorDialog.show();
            }
        });

    }

    @Override
    public void OnclickItem(int position) {
        Toast.makeText(getContext(), "click "+ position, Toast.LENGTH_SHORT).show();
        //Navigation.findNavController(getView()).navigate(R.id.action_FirstFragment_to_detailFragment);

    }
}
//findNavController().navigate(R.id.seeAllNowPlayingFragment)