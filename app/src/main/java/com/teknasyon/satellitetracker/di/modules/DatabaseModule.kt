package com.teknasyon.satellitetracker.di.modules

import android.content.Context
import androidx.room.Room
import com.teknasyon.satellitetracker.data.source.local.room.SatelliteDetailsDao
import com.teknasyon.satellitetracker.data.source.local.room.SatelliteDetailsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideSatelliteDetailsDatabase(@ApplicationContext context: Context): SatelliteDetailsDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = SatelliteDetailsDatabase::class.java,
            name = SatelliteDetailsDatabase.SATELLITE_DETAILS_DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideSatelliteDetailsDao(satelliteDetailsDatabase: SatelliteDetailsDatabase): SatelliteDetailsDao =
        satelliteDetailsDatabase.satelliteDetailsDao()
}