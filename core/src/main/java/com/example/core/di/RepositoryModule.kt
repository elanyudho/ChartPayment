package com.example.core.di

import com.example.core.data.repository.ChartRepositoryImpl
import com.example.core.model.repository.ChartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {
    @Binds
    @ActivityScoped
    abstract fun bindChartRepository(repositoryImpl: ChartRepositoryImpl): ChartRepository
}