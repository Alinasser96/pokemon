package com.whiteside.bokemoncourse.ui.fav

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.benipal.wholesale.utils.Status
import com.whiteside.bokemoncourse.R
import com.whiteside.bokemoncourse.adapter.PokemonsAdapter
import com.whiteside.bokemoncourse.base.BaseActivity
import com.whiteside.bokemoncourse.databinding.ActivityFavBinding
import com.whiteside.bokemoncourse.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavActivity : BaseActivity() {
    lateinit var viewModel: FavViewModel
    lateinit var bind: ActivityFavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = DataBindingUtil.setContentView(this, R.layout.activity_fav)
        viewModel = ViewModelProvider(this).get(FavViewModel::class.java)

        setupSwipe()

        viewModel.getFavPokemons().observe(this) {
            if (it.status === Status.SUCCESS) {
                handleSuccess()
                bind.pokemons.adapter = PokemonsAdapter(it.data!!)
            } else if (it.status === Status.LOADING) {
                handleLoading()
            } else {
                handleFailure(it.error!!)
            }
        }
    }

    private fun setupSwipe() {
        val callback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    deletePokemon(viewHolder.adapterPosition)
                }
            }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(bind.pokemons)
    }

    private fun deletePokemon(position: Int) {
        val name = (bind.pokemons.adapter!! as PokemonsAdapter).getPokemon(position).name

        viewModel.deletePokemon(name).observe(this@FavActivity) {
            if (it.status === Status.SUCCESS) {
                handleSuccess()

                bind.pokemons.adapter!!.notifyDataSetChanged()
                Toast.makeText(
                    this@FavActivity,
                    "pokemon removed from database",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (it.status === Status.LOADING) {
                handleLoading()
            } else {
                handleFailure(it.error!!)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        bind.favButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}