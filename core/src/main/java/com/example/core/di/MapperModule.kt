package com.example.core.di

import com.example.core.data.remote.mapper.ChartMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object MapperModule {
    @Provides
    @ActivityScoped
    fun provideChartMapper() = ChartMapper()

}