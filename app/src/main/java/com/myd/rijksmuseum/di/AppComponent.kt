package com.myd.rijksmuseum.di

import dagger.Component

@Component(dependencies = [CoreModuleDependencies::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(
            loginModuleDependencies: CoreModuleDependencies
        ): AppComponent
    }
}
