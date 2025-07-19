package com.example.pokedexandroid.data.model

import com.google.gson.annotations.SerializedName

data class PokemonSpecies(
    val id: Int,
    val name: String,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>,
    val genera: List<Genus>,
    @SerializedName("generation")
    val generation: NamedApiResource,
    @SerializedName("evolves_from_species")
    val evolvesFromSpecies: NamedApiResource?,
    @SerializedName("evolution_chain")
    val evolutionChain: EvolutionChainResource,
    @SerializedName("capture_rate")
    val captureRate: Int,
    @SerializedName("base_happiness")
    val baseHappiness: Int?,
    @SerializedName("growth_rate")
    val growthRate: NamedApiResource
)

data class FlavorTextEntry(
    @SerializedName("flavor_text")
    val flavorText: String,
    val language: NamedApiResource,
    val version: NamedApiResource
)

data class Genus(
    val genus: String,
    val language: NamedApiResource
)

data class EvolutionChainResource(
    val url: String
)