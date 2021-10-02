package com.myd.rijksmuseum.di

import com.myd.rijksmuseum.data.CollectionDataSource
import com.myd.rijksmuseum.data.DetailsDataSource
import com.myd.rijksmuseum.data.NetworkService
import com.myd.rijksmuseum.usecases.GetCollectionsUseCase
import com.myd.rijksmuseum.usecases.GetDetailsUseCase
import dagger.BindsInstance
import dagger.Component

@CoreScope
@Component(modules = [CoreModule::class])
interface CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance networkService: NetworkService,
            @BindsInstance collectionDataSource: CollectionDataSource,
            @BindsInstance detailsDataSource: DetailsDataSource
        ): CoreComponent
    }

    fun getCollectionsUseCase(): GetCollectionsUseCase
    fun getDetailsUseCase(): GetDetailsUseCase
}