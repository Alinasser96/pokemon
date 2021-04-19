package com.whiteside.bokemoncourse.ui.main

import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.whiteside.bokemoncourse.adapter.PokemonsAdapter
import com.whiteside.bokemoncourse.model.Pokemon
import com.whiteside.bokemoncourse.model.PokemonListResult
import com.whiteside.bokemoncourse.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: PokemonRepository,
) : ViewModel() {

    var pokemonList = PokemonListResult()

    companion object {
        @JvmStatic
        @BindingAdapter("pokemons")
        fun adaptPokemons(view: RecyclerView, list: List<Pokemon>?) {
            list?.let {
                view.adapter = PokemonsAdapter(list)
            }
        }
    }

    fun getPokemons() {
        repository.getPokemons()
            .subscribeOn(Schedulers.io())
            .map {
                for (i: Int in 0 until it.results!!.size) {
                    it.results!![i].id = i + 1

                    it.results!![i].url =
                        "https://pokeres.bastionbot.org/images/pokemon/${i + 1}.png"
                }
                it.results
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                pokemonList.results = it
            }
    }

    fun insertPokemon(pokemon: Pokemon) {
        repository.insertPokemon(pokemon)
    }
}