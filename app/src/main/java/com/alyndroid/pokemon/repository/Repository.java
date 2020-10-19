package com.alyndroid.pokemon.repository;

import androidx.lifecycle.LiveData;

import com.alyndroid.pokemon.db.PokemonDao;
import com.alyndroid.pokemon.model.Pokemon;
import com.alyndroid.pokemon.model.PokemonResponse;
import com.alyndroid.pokemon.network.PokemonApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class Repository {
    private PokemonApiService pokemonApiService;
    private PokemonDao pokemonDao;

    @Inject
    public Repository(PokemonApiService pokemonApiService, PokemonDao pokemonDao) {
        this.pokemonApiService = pokemonApiService;
        this.pokemonDao = pokemonDao;
    }

    public Observable<PokemonResponse> getPokemons(){
        return pokemonApiService.getPokemons();
    }

    public void insertPokemon(Pokemon pokemon){pokemonDao.insertPokemon(pokemon);}

    public void deletePokemon(String pokemonName){pokemonDao.deletePokemon(pokemonName);}

    public LiveData<List<Pokemon>> getFavPokemon(){
        return pokemonDao.getPokemons();
    }
}
