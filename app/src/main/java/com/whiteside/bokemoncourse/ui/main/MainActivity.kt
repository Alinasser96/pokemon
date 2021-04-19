package com.whiteside.bokemoncourse.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.RecyclerView
import com.whiteside.bokemoncourse.R
import com.whiteside.bokemoncourse.adapter.PokemonsAdapter
import com.whiteside.bokemoncourse.databinding.ActivityMainBinding
import com.whiteside.bokemoncourse.ui.fav.FavActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setupSwipe()

        bind.viewModel = viewModel
        viewModel.getPokemons()
    }


    private fun setupSwipe() {
        val callback: SimpleCallback = object : SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val pokemon = (bind.pokemons.adapter!! as PokemonsAdapter).getPokemon(position)
                viewModel.insertPokemon(pokemon)
                bind.pokemons.adapter!!.notifyDataSetChanged()
                Toast.makeText(this@MainActivity, "pokemon added to database", Toast.LENGTH_SHORT).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(bind.pokemons)
    }

    override fun onStart() {
        super.onStart()

        bind.favButton.setOnClickListener{
            startActivity(Intent(this, FavActivity::class.java))
            finish()
        }
    }
}