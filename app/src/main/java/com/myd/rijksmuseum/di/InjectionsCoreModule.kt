package com.myd.rijksmuseum.di

import android.content.Context
import com.myd.rijksmuseum.data.CollectionDataSource
import com.myd.rijksmuseum.data.DetailsDataSource
import com.myd.rijksmuseum.data.NetworkService
import com.myd.rijksmuseum.framework.RetrofitNetworkService
import com.myd.rijksmuseum.framework.datasource.RoomCollectionDataSource
import com.myd.rijksmuseum.framework.datasource.RoomDetailsDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class InjectionsCoreModule {

    companion object {
        @Singleton
        @Provides
        fun provideAppComponent(
            @ApplicationContext context: Context
        ): AppComponent =
            DaggerAppComponent.factory()
                .create(
                    EntryPointAccessors.fromApplication(
                        context,
                        CoreModuleDependencies::class.java
                    )
                )
    }

    @Singleton
    @Binds
    abstract fun provideNetworkService(networkService: RetrofitNetworkService): NetworkService

    @Singleton
    @Binds
    abstract fun provideRoomCollectionDataSource(
        collectionDataSource: RoomCollectionDataSource
    ): CollectionDataSource

    @Singleton
    @Binds
    abstract fun provideDetailsDataSource(
        detailsDataSource: RoomDetailsDataSource
    ): DetailsDataSource
}