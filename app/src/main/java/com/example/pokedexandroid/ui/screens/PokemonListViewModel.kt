package com.example.pokedexandroid.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexandroid.data.model.PokemonListItem
import com.example.pokedexandroid.data.repository.PokemonRepository
import com.example.pokedexandroid.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    var pokemonList by mutableStateOf<List<PokemonListItem>>(emptyList())
        private set
    
    var isLoading by mutableStateOf(false)
        private set
    
    var error by mutableStateOf("")
        private set
    
    var searchQuery by mutableStateOf("")
        private set
    
    private var cachedPokemonList = listOf<PokemonListItem>()
    
    init {
        loadPokemonList()
    }
    
    fun loadPokemonList() {
        repository.getPokemonList().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    pokemonList = result.data ?: emptyList()
                    cachedPokemonList = pokemonList
                    isLoading = false
                    error = ""
                }
                is Resource.Error -> {
                    error = result.message ?: "An unexpected error occurred"
                    isLoading = false
                }
                is Resource.Loading -> {
                    isLoading = true
                    error = ""
                }
            }
        }.launchIn(viewModelScope)
    }
    
    fun searchPokemon(query: String) {
        searchQuery = query
        pokemonList = if (query.isBlank()) {
            cachedPokemonList
        } else {
            cachedPokemonList.filter {
                it.name.contains(query.lowercase(), ignoreCase = true) ||
                it.id.toString() == query
            }
        }
    }
}