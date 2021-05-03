package com.whiteside.bokemoncourse.model

import androidx.annotation.NonNull
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.whiteside.bokemoncourse.BR


@Entity(tableName = "fav_pokemons")
class Pokemon : BaseObservable() {

    @NonNull
    @PrimaryKey
    lateinit var name: String

    @get:Bindable
    var url: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.url)
        }
}
