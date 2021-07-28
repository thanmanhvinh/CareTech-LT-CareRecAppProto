/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/26/19 9:25 AM
 *
 */

package com.base.mvvm.common.di.module;

import com.base.mvvm.main.view.fragment.demo.DetailFragment;
import com.base.mvvm.main.view.fragment.DiaryFragment;
import com.base.mvvm.main.view.fragment.ListUserFragment;
import com.base.mvvm.main.view.fragment.HistoryFragment;
import com.base.mvvm.main.view.fragment.NoteFragment;
import com.base.mvvm.main.view.fragment.ReportFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * FragmentModule for setting dagger
 */
@Module
public class FragmentModule {

    @Provides
    @Singleton
    NoteFragment provideHomeFragment() {
        return NoteFragment.newsInstance();
    }

    @Provides
    @Singleton
    DiaryFragment provideEventsFragment() {
        return DiaryFragment.newsInstance();
    }

    @Provides
    @Singleton
    HistoryFragment provideThirdFragment() {
        return HistoryFragment.newsInstance();
    }

    @Provides
    @Singleton
    ReportFragment provideFourthFragment() {
        return ReportFragment.newsInstance();
    }

    @Provides
    @Singleton
    ListUserFragment provideFifthFragment() {
        return ListUserFragment.newsInstance();
    }

    @Provides
    @Singleton
    DetailFragment provideDetailFragment() {
        return DetailFragment.newsInstance();
    }

}