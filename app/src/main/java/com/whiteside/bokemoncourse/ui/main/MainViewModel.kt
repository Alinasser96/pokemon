package com.whiteside.bokemoncourse.ui.main

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.RecyclerView
import com.benipal.wholesale.utils.Resource
import com.whiteside.bokemoncourse.adapter.PokemonsAdapter
import com.whiteside.bokemoncourse.model.Pokemon
import com.whiteside.bokemoncourse.model.PokemonListResult
import com.whiteside.bokemoncourse.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: PokemonRepository,
) : ViewModel() {

//    var pokemonList = PokemonListResult()
//
//    companion object {
//        @JvmStatic
//        @BindingAdapter("pokemons")
//        fun adaptPokemons(view: RecyclerView, list: List<Pokemon>?) {
//            list?.let {
//                view.adapter = PokemonsAdapter(list)
//            }
//        }
//    }

    fun getPokemons() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            var id = 1
            val result = repository.getPokemons().results!!.map {
                it.url = "https://pokeres.bastionbot.org/images/pokemon/${id++}.png"
                it
            }
            emit(Resource.success(result))
        } catch (e: Exception) {
            emit(Resource.error(null, e))
        }
    }

    fun insertPokemon(pokemon: Pokemon) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.insertPokemon(pokemon)))
        } catch (e: Exception) {
            emit(Resource.error(null, e))
        }
    }
}