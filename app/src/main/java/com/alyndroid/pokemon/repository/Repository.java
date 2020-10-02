package com.alyndroid.pokemon.repository;

import com.alyndroid.pokemon.model.PokemonResponse;
import com.alyndroid.pokemon.network.PokemonApiService;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class Repository {
    private PokemonApiService pokemonApiService;

    @Inject
    public Repository(PokemonApiService pokemonApiService) {
        this.pokemonApiService = pokemonApiService;
    }

    public Observable<PokemonResponse> getPokemons(){
        return pokemonApiService.getPokemons();
    }
}
