package com.myd.rijksmuseum.di

import dagger.Component

@Component(dependencies = [CoreModuleDependencies::class])
interface AppComponent {
    // TODO factory ile olur mu ki?
    @Component.Builder
    interface Builder {
        fun appDependencies(loginModuleDependencies: CoreModuleDependencies): Builder
        fun build(): AppComponent
    }
}
