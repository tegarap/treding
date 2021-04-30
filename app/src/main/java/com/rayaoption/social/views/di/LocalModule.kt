package com.rayaoption.social.views.di

import android.content.Context
import androidx.room.Room
import com.rayaoption.social.views.data.local.db.AppDB
import com.rayaoption.social.views.data.local.db.MovieFavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object LocalModule {

    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context): AppDB = Room
        .databaseBuilder(context, AppDB::class.java, "mymovie")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideCryptoDao(appDB: AppDB): MovieFavoriteDao = appDB.favoriteDao()

}