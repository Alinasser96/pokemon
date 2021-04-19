package com.whiteside.bokemoncourse.repository

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
    fun getPokemons(): Observable<PokemonListResult> {
        return api.getPokemons()
    }

    fun insertPokemon(pokemon: Pokemon) = dao.insertPokemon(pokemon)

    fun getFavPokemons() = dao.getPokemons()

    fun deletePokemon(id: Int) = dao.deletePokemon(id)
}