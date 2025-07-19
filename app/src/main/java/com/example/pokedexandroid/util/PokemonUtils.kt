package com.example.pokedexandroid.util

import androidx.compose.ui.graphics.Color
import com.example.pokedexandroid.ui.theme.*
import java.util.Locale

fun getTypeColor(type: String): Color {
    return when (type.lowercase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Gray
    }
}

fun getStatColor(stat: String): Color {
    return when (stat.lowercase(Locale.ROOT)) {
        "hp" -> HPColor
        "attack" -> AttackColor
        "defense" -> DefenseColor
        "special-attack" -> SpAttackColor
        "special-defense" -> SpDefenseColor
        "speed" -> SpeedColor
        else -> Color.Gray
    }
}

fun getStatAbbreviation(stat: String): String {
    return when (stat.lowercase(Locale.ROOT)) {
        "hp" -> "HP"
        "attack" -> "ATK"
        "defense" -> "DEF"
        "special-attack" -> "SP.ATK"
        "special-defense" -> "SP.DEF"
        "speed" -> "SPD"
        else -> stat.uppercase(Locale.ROOT)
    }
}

fun formatPokemonId(id: Int): String {
    return "#${id.toString().padStart(3, '0')}"
}