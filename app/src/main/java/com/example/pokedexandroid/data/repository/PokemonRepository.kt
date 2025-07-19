package com.example.pokedexandroid.data.repository

import com.example.pokedexandroid.data.api.PokeApiService
import com.example.pokedexandroid.data.model.Pokemon
import com.example.pokedexandroid.data.model.PokemonListItem
import com.example.pokedexandroid.data.model.PokemonSpecies
import com.example.pokedexandroid.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(
    private val api: PokeApiService
) {
    fun getPokemonList(limit: Int = 251, offset: Int = 0): Flow<Resource<List<PokemonListItem>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getPokemonList(limit, offset)
            emit(Resource.Success(response.results))
        } catch (e: HttpException) {
            emit(Resource.Error("An unexpected error occurred: ${e.message()}"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    fun getPokemonDetails(pokemonId: Int): Flow<Resource<Pokemon>> = flow {
        emit(Resource.Loading())
        try {
            val pokemon = api.getPokemon(pokemonId)
            emit(Resource.Success(pokemon))
        } catch (e: HttpException) {
            emit(Resource.Error("An unexpected error occurred: ${e.message()}"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    fun getPokemonSpecies(pokemonId: Int): Flow<Resource<PokemonSpecies>> = flow {
        emit(Resource.Loading())
        try {
            val species = api.getPokemonSpecies(pokemonId)
            emit(Resource.Success(species))
        } catch (e: HttpException) {
            emit(Resource.Error("An unexpected error occurred: ${e.message()}"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}