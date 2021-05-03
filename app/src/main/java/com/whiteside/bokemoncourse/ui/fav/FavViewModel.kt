package com.whiteside.bokemoncourse.ui.fav

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.RecyclerView
import com.benipal.wholesale.utils.Resource
import com.benipal.wholesale.utils.Status
import com.whiteside.bokemoncourse.adapter.PokemonsAdapter
import com.whiteside.bokemoncourse.model.Pokemon
import com.whiteside.bokemoncourse.model.PokemonListResult
import com.whiteside.bokemoncourse.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    val repository: PokemonRepository,
) : ViewModel() {

    fun getFavPokemons() = liveData {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.getFavPokemons()))
        } catch (e : Exception) {
            emit(Resource.error(null, e))
        }
    }

    fun deletePokemon(name: String) = liveData {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.deletePokemon(name)))
        } catch (e : Exception) {
            emit(Resource.error(null, e))
        }
    }
}