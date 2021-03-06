package com.myd.rijksmuseum.di

import android.content.Context
import com.myd.rijksmuseum.framework.RijksMuseumApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindingModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<RijksMuseumApp> {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance @ApplicationContext context: Context
        ): AppComponent
    }
}
