package com.alyndroid.pokemon.viewmodels;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alyndroid.pokemon.model.Pokemon;
import com.alyndroid.pokemon.model.PokemonResponse;
import com.alyndroid.pokemon.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<ArrayList<Pokemon>> pokemonList = new MutableLiveData<>();

    @ViewModelInject
    public PokemonViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Pokemon>> getPokemonList() {
        return pokemonList;
    }


        @SuppressLint("CheckResult")
        public void getPokemons(){
            repository.getPokemons()
                    .subscribeOn(Schedulers.io())
                    .map(new Function<PokemonResponse, ArrayList<Pokemon>>() {
                        @Override
                        public ArrayList<Pokemon> apply(PokemonResponse pokemonResponse) throws Throwable {
                            ArrayList<Pokemon> list = pokemonResponse.getResults();
                            for(Pokemon pokemon : list){
                                String url = pokemon.getUrl();
                                String[] pokemonIndex = url.split("/");
                                pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/"+pokemonIndex[pokemonIndex.length-1] +".png");
                            }
                            return list;
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> pokemonList.setValue(result),
                            error-> Log.e("viwModel", error.getMessage() ));
    }




}
