package com.myd.rijksmuseum.di

import android.content.Context
import androidx.room.Room
import com.myd.rijksmuseum.framework.RetrofitNetworkService
import com.myd.rijksmuseum.framework.datasource.RoomCollectionDataSource
import com.myd.rijksmuseum.framework.datasource.RoomDetailsDataSource
import com.myd.rijksmuseum.framework.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {
    private const val DATABASE_NAME = "rijks-museum-db"

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideCollectionDao(db: AppDatabase) = db.collectionDao()

    @Singleton
    @Provides
    fun provideDetailsDao(db: AppDatabase) = db.detailsDao()

    @Singleton
    @Provides
    fun provideNetworkService() = RetrofitNetworkService()

    @Singleton
    @Provides
    fun provideCoreComponent(
        networkService: RetrofitNetworkService,
        collectionDataSource: RoomCollectionDataSource,
        detailsDataSource: RoomDetailsDataSource
    ): CoreComponent =
        DaggerCoreComponent.factory().create(
            networkService,
            collectionDataSource,
            detailsDataSource
        )

    @Singleton
    @Provides
    fun provideGetCollectionsUseCase(
        coreComponent: CoreComponent
    ) = coreComponent.getCollectionsUseCase()

    @Singleton
    @Provides
    fun provideGetDetailsUseCase(
        coreComponent: CoreComponent
    ) = coreComponent.getDetailsUseCase()
}

