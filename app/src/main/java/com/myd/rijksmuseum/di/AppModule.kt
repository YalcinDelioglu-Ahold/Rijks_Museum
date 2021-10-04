package com.myd.rijksmuseum.di

import android.content.Context
import androidx.room.Room
import com.myd.rijksmuseum.framework.RetrofitNetworkService
import com.myd.rijksmuseum.framework.datasource.RoomCollectionDataSource
import com.myd.rijksmuseum.framework.datasource.RoomDetailsDataSource
import com.myd.rijksmuseum.framework.db.AppDatabase
import com.myd.rijksmuseum.presentation.di.NavHostModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [NavHostModule::class])
object AppModule {
    private const val DATABASE_NAME = "rijks-museum-db"
    private const val BASE_URL = "https://www.rijksmuseum.nl/api/nl/"

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
    fun provideRetrofit(): Retrofit = Retrofit.Builder().apply {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        baseUrl(BASE_URL)
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create())
    }.build()

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

