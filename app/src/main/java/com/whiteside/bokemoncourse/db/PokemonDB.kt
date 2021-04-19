package com.whiteside.bokemoncourse.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.whiteside.bokemoncourse.model.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false )
abstract class PokemonDB : RoomDatabase() {

    abstract fun getDao() : PokemonDao;
}