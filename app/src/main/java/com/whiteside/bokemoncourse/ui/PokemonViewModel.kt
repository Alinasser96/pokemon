package com.whiteside.bokemoncourse.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.whiteside.bokemoncourse.model.Pokemon

class PokemonViewModel : ViewModel() {

    var _pokemon = MutableLiveData<Pokemon>()
    var pokemon: LiveData<Pokemon> = _pokemon

    companion object {
        @JvmStatic
        @BindingAdapter("name")
        fun adaptPokemonName(view: TextView, pokemon: Pokemon?) {
            pokemon?.let {
                view.text = pokemon.name
            }
        }

        @JvmStatic
        @BindingAdapter("image")
        fun adaptPokemonImage(view: ImageView, pokemon: Pokemon?) {
            pokemon?.let {
                Glide.with(view)
                    .load(pokemon.url)
                    .into(view)
            }
        }
    }
}