package com.myd.rijksmuseum.di

import com.myd.rijksmuseum.usecases.GetCollectionsUseCase
import com.myd.rijksmuseum.usecases.GetDetailsUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface CoreModuleDependencies {
    fun getCollectionsUseCase(): GetCollectionsUseCase
    fun getDetailsUseCase(): GetDetailsUseCase
}