package com.example.core.di

import com.example.core.model.repository.ChartRepository
import com.example.core.model.usecase.GetChartsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object UseCaseModule {
    @Provides
    @ActivityScoped
    fun provideChartsUseCase(repository: ChartRepository) = GetChartsUseCase(repository)
}

