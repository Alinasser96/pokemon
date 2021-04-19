package com.whiteside.bokemoncourse.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.whiteside.bokemoncourse.model.Pokemon

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPokemon(pokemon: Pokemon)

    @Query("select * from fav_pokemons")
    fun getPokemons() : List<Pokemon>

    @Query("delete from fav_pokemons where id =:id")
    fun deletePokemon(id: Int)
}