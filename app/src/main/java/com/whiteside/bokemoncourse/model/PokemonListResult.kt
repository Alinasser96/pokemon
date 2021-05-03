package com.whiteside.bokemoncourse.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.whiteside.bokemoncourse.BR

class PokemonListResult : BaseObservable() {

    @get:Bindable
    var results: List<Pokemon>? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.results)
        }
}