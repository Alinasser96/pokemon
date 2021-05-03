package com.whiteside.bokemoncourse.repository

import androidx.lifecycle.liveData
import com.whiteside.bokemoncourse.db.PokemonDao
import com.whiteside.bokemoncourse.model.Pokemon
import com.whiteside.bokemoncourse.model.PokemonListResult
import com.whiteside.bokemoncourse.network.PokemonAPI
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


class PokemonRepository @Inject constructor(
    private var api: PokemonAPI,
    private var dao: PokemonDao
) {
    suspend fun getPokemons() =
        api.getPokemons()

    suspend fun insertPokemon(pokemon: Pokemon) = dao.insertPokemon(pokemon)

    suspend fun getFavPokemons() = dao.getPokemons()

    suspend fun deletePokemon(name: String) = dao.deletePokemon(name)
}