package com.myd.rijksmuseum.di

import com.myd.rijksmuseum.presentation.MainActivity
import com.myd.rijksmuseum.presentation.di.NavHostModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [NavHostModule::class])
    abstract fun mainActivity(): MainActivity
}
