package com.example.pokedexandroid.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedexandroid.ui.theme.LightGray
import com.example.pokedexandroid.util.getStatAbbreviation
import com.example.pokedexandroid.util.getStatColor

@Composable
fun PokemonStatBar(
    statName: String,
    statValue: Int,
    maxValue: Int = 255,
    modifier: Modifier = Modifier
) {
    var animationPlayed by remember { mutableStateOf(false) }
    val animatedValue by animateFloatAsState(
        targetValue = if (animationPlayed) statValue.toFloat() else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "stat animation"
    )
    
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = getStatAbbreviation(statName),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.width(60.dp)
        )
        
        Text(
            text = statValue.toString().padStart(3, '0'),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.width(40.dp)
        )
        
        Box(
            modifier = Modifier
                .weight(1f)
                .height(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(animatedValue / maxValue)
                    .clip(RoundedCornerShape(8.dp))
                    .background(getStatColor(statName))
            )
        }
    }
}