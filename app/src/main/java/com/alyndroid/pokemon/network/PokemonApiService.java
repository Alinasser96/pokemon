package com.alyndroid.pokemon.network;

import com.alyndroid.pokemon.model.PokemonResponse;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface PokemonApiService {

    @GET("pokemon")
    Observable<PokemonResponse> getPokemons();
}
