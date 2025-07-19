package com.example.pokedexandroid.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexandroid.data.model.Pokemon
import com.example.pokedexandroid.data.model.PokemonSpecies
import com.example.pokedexandroid.data.repository.PokemonRepository
import com.example.pokedexandroid.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    var pokemonDetail by mutableStateOf<Pokemon?>(null)
        private set
    
    var pokemonSpecies by mutableStateOf<PokemonSpecies?>(null)
        private set
    
    var isLoading by mutableStateOf(false)
        private set
    
    var error by mutableStateOf("")
        private set
    
    init {
        savedStateHandle.get<String>("pokemonId")?.let { pokemonId ->
            pokemonId.toIntOrNull()?.let { id ->
                loadPokemonDetail(id)
                loadPokemonSpecies(id)
            }
        }
    }
    
    private fun loadPokemonDetail(pokemonId: Int) {
        repository.getPokemonDetails(pokemonId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    pokemonDetail = result.data
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
    
    private fun loadPokemonSpecies(pokemonId: Int) {
        repository.getPokemonSpecies(pokemonId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    pokemonSpecies = result.data
                }
                is Resource.Error -> {
                    // Species is optional, don't show error
                }
                is Resource.Loading -> {
                    // Loading handled by main pokemon detail
                }
            }
        }.launchIn(viewModelScope)
    }
}