package com.whiteside.bokemoncourse.ui.fav

import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.whiteside.bokemoncourse.adapter.PokemonsAdapter
import com.whiteside.bokemoncourse.model.Pokemon
import com.whiteside.bokemoncourse.model.PokemonListResult
import com.whiteside.bokemoncourse.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    val repository: PokemonRepository,
) : ViewModel() {

    var favPokemonList = PokemonListResult()

    companion object {
        @JvmStatic
        @BindingAdapter("pokemons")
        fun adaptPokemons(view: RecyclerView, list: List<Pokemon>?) {
            list?.let {
                view.adapter = PokemonsAdapter(list)
            }
        }
    }

    fun getFavPokemons() {
        favPokemonList.results = repository.getFavPokemons()
    }

    fun deletePokemon(id : Int) {
        repository.deletePokemon(id)
    }
}