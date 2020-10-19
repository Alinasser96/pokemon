package com.alyndroid.pokemon.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.alyndroid.pokemon.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PokemonDao {

    @Insert
    public void insertPokemon(Pokemon pokemon);

    @Query("delete from fav_table where name =:pokemonName")
    public void deletePokemon(String pokemonName);

    @Query("select * from fav_table")
    public LiveData<List<Pokemon>> getPokemons();
}
