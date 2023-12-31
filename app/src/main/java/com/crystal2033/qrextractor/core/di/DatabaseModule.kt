package com.crystal2033.qrextractor.core.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.crystal2033.qrextractor.core.localdb.AppDatabase
import com.crystal2033.qrextractor.scanner_feature.general.repository_impl.ScannedGroupRepositoryImpl
import com.crystal2033.qrextractor.scanner_feature.scanner.domain.repository.ScannedGroupCreatorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "app_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}