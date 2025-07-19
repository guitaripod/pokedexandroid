package com.example.pokedexandroid.data.api

import com.example.pokedexandroid.data.model.Pokemon
import com.example.pokedexandroid.data.model.PokemonListResponse
import com.example.pokedexandroid.data.model.PokemonSpecies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 251,
        @Query("offset") offset: Int = 0
    ): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): Pokemon

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): Pokemon

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path("id") id: Int): PokemonSpecies

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}