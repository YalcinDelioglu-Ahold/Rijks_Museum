package com.myd.rijksmuseum.framework

import com.myd.rijksmuseum.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class RijksMuseumApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out RijksMuseumApp> {
        return DaggerAppComponent.factory().create(
            this
        )
    }
}
