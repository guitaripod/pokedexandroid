package com.example.pokedexandroid.data.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val types: List<PokemonType>,
    val stats: List<PokemonStat>,
    val abilities: List<PokemonAbility>,
    @SerializedName("base_experience")
    val baseExperience: Int?,
    val species: NamedApiResource,
    val moves: List<PokemonMove>
)

data class Sprites(
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?,
    @SerializedName("back_default")
    val backDefault: String?,
    @SerializedName("back_shiny")
    val backShiny: String?,
    val other: OtherSprites?
)

data class OtherSprites(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork?
)

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?
)

data class PokemonType(
    val slot: Int,
    val type: NamedApiResource
)

data class PokemonStat(
    @SerializedName("base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: NamedApiResource
)

data class PokemonAbility(
    val ability: NamedApiResource,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val slot: Int
)

data class PokemonMove(
    val move: NamedApiResource,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>
)

data class VersionGroupDetail(
    @SerializedName("level_learned_at")
    val levelLearnedAt: Int,
    @SerializedName("move_learn_method")
    val moveLearnMethod: NamedApiResource,
    @SerializedName("version_group")
    val versionGroup: NamedApiResource
)

data class NamedApiResource(
    val name: String,
    val url: String
)