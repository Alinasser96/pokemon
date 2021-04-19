package com.whiteside.bokemoncourse.ui.fav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.whiteside.bokemoncourse.R
import com.whiteside.bokemoncourse.adapter.PokemonsAdapter
import com.whiteside.bokemoncourse.databinding.ActivityFavBinding
import com.whiteside.bokemoncourse.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavActivity : AppCompatActivity() {
    lateinit var viewModel: FavViewModel
    lateinit var bind: ActivityFavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = DataBindingUtil.setContentView(this, R.layout.activity_fav)
        viewModel = ViewModelProvider(this).get(FavViewModel::class.java)

        setupSwipe()

        bind.viewModel = viewModel
        viewModel.getFavPokemons()
    }

    private fun setupSwipe() {
        val callback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val id = (bind.pokemons.adapter!! as PokemonsAdapter).getPokemon(position).id!!
                viewModel.deletePokemon(id)
                bind.pokemons.adapter!!.notifyDataSetChanged()
                Toast.makeText(this@FavActivity, "pokemon removed from database", Toast.LENGTH_SHORT).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(bind.pokemons)
    }


    override fun onStart() {
        super.onStart()

        bind.favButton.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}