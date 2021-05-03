package com.whiteside.bokemoncourse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.whiteside.bokemoncourse.BR
import com.whiteside.bokemoncourse.R
import com.whiteside.bokemoncourse.databinding.CardViewPokemonBinding
import com.whiteside.bokemoncourse.model.Pokemon
import com.whiteside.bokemoncourse.ui.PokemonViewModel

class PokemonsAdapter(private var list: List<Pokemon>) :
    RecyclerView.Adapter<PokemonsAdapter.PokemonHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        return PokemonHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.card_view_pokemon,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getPokemon(position: Int) : Pokemon = list[position]

    open inner class PokemonHolder(val bind: CardViewPokemonBinding) :
        RecyclerView.ViewHolder(bind.root) {

        val viewModel = PokemonViewModel()
        init {
            bind.viewModel = viewModel
        }

        fun setData(pokemon: Pokemon) {
            viewModel._pokemon.value = pokemon
        }
    }
}