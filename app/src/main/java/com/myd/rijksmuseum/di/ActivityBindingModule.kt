package com.myd.rijksmuseum.di

import com.myd.rijksmuseum.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity

    @Module
    internal interface MainActivityModule {
        @Binds
        fun bindMainActivity(mainActivity: MainActivity): MainActivity
    }
}
