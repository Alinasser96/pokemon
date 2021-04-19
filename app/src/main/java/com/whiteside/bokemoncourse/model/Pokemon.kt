package com.whiteside.bokemoncourse.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.whiteside.bokemoncourse.BR


@Entity(tableName = "fav_pokemons")
class Pokemon : BaseObservable() {

    @PrimaryKey
    var id : Int? = null

    @get:Bindable
    var name: String? = null
    set(value) {
        field = value
        notifyPropertyChanged(BR.name)
    }

    @get:Bindable
    var url: String? = null
    set(value) {
        field = value
        notifyPropertyChanged(BR.url)
    }
}
