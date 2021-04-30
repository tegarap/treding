package com.rayaoption.social.views.di

import com.rayaoption.social.views.data.local.db.MovieFavoriteDao
import com.rayaoption.social.views.data.network.MovieService
import com.rayaoption.social.views.data.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(
        movieService: MovieService,
        movieFavDao: MovieFavoriteDao
    ): AppRepository = AppRepository(movieService, movieFavDao)



}