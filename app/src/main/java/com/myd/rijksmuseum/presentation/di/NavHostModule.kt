package com.myd.rijksmuseum.presentation.di

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NavHostModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    abstract fun navHostFragmentInjector(): InjectingNavHostFragment
}