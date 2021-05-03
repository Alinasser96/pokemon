package com.whiteside.bokemoncourse.di

import android.app.Application
import androidx.room.Room
import com.whiteside.bokemoncourse.db.PokemonDB
import com.whiteside.bokemoncourse.db.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    companion object {
        @Provides
        @Singleton
        fun provideDB(application: Application): PokemonDB =
            Room.databaseBuilder(application, PokemonDB::class.java, "fav_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

        @Provides
        @Singleton
        fun provideDao(pokemonDB: PokemonDB): PokemonDao = pokemonDB.getDao()
    }
}