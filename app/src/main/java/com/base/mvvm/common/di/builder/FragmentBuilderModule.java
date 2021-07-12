/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/26/19 9:25 AM
 *
 */

package com.base.mvvm.common.di.builder;

import com.base.mvvm.main.view.fragment.ListUserFragment;
import com.base.mvvm.main.view.fragment.HistoryFragment;
import com.base.mvvm.main.view.fragment.NoteFragment;
import com.base.mvvm.main.view.fragment.ReportFragment;
import com.base.mvvm.main.view.fragment.DiaryFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * FragmentBuilderModule for settinsettingg dagger
 */
@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract NoteFragment homeFragment();

    @ContributesAndroidInjector
    abstract DiaryFragment eventsFragment();

    @ContributesAndroidInjector
    abstract HistoryFragment thirdFragment();

    @ContributesAndroidInjector
    abstract ReportFragment fourthFragment();

    @ContributesAndroidInjector
    abstract ListUserFragment fifthFragment();

}
