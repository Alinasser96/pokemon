package com.whiteside.bokemoncourse.network

import com.whiteside.bokemoncourse.model.PokemonListResult
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET


interface PokemonAPI {

    @GET("pokemon")
    fun getPokemons() : Observable<PokemonListResult>
}