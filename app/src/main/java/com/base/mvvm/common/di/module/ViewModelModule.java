/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/26/19 9:25 AM
 *
 */

package com.base.mvvm.common.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.base.mvvm.common.utils.ViewModelFactory;
import com.base.mvvm.main.viewmodel.ActivityMainViewModel;
import com.base.mvvm.main.viewmodel.DiaryViewModel;
import com.base.mvvm.main.viewmodel.ListUserViewModel;
import com.base.mvvm.main.viewmodel.HistoryViewModel;
import com.base.mvvm.main.viewmodel.NoteViewModel;
import com.base.mvvm.main.viewmodel.ReportViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * ViewModelModule
 */
@Module
public abstract class ViewModelModule {


    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(ActivityMainViewModel.class)
    abstract ViewModel bindsMainViewModel(ActivityMainViewModel activityMainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel.class)
    abstract ViewModel bindsFirstFragViewModel(NoteViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DiaryViewModel.class)
    abstract ViewModel bindsSecondFragViewModel(DiaryViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel.class)
    abstract ViewModel bindThirdFragViewModel(HistoryViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ReportViewModel.class)
    abstract ViewModel bindFourthFragViewModel(ReportViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ListUserViewModel.class)
    abstract ViewModel bindFifthFragViewModel(ListUserViewModel viewModel);
}
