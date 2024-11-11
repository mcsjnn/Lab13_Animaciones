package com.example.lab13animaciones

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp

@Composable
fun AnimatedScreen() {
    var isDarkMode by remember { mutableStateOf(false) }
    var isButtonVisible by remember { mutableStateOf(true) }
    var size by remember { mutableStateOf(100.dp) }
    var color by remember { mutableStateOf(Color.Blue) }

    val backgroundColor by animateColorAsState(
        targetValue = if (isDarkMode) Color(0xFF121212) else Color(0xFFF1F1F1),
        animationSpec = tween(durationMillis = 500), label = ""
    )

    val animatedSize by animateDpAsState(
        targetValue = size,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Elemento que cambia de tamaño y color al hacer clic
            AnimatedSizeColorBox(
                animatedSize = animatedSize,
                color = color,
                onClick = {
                    size = if (size.value == 100f) 200.dp else 100.dp
                    color = if (color == Color.Blue) Color.Green else Color.Blue
                }
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Botón que se desplaza y desaparece
            AnimatedVisibility(
                visible = isButtonVisible,
                enter = fadeIn(animationSpec = tween(durationMillis = 300)),
                exit = slideOutHorizontally(animationSpec = tween(durationMillis = 300))
            ) {
                Button(onClick = {
                    isButtonVisible = false
                }) {
                    Text("Desaparecer")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Botón para alternar entre modo claro y oscuro
            ModeToggleButton(isDarkMode = isDarkMode) {
                isDarkMode = !isDarkMode
            }
        }
    }
}

// Elemento que cambia de tamaño y color
@Composable
fun AnimatedSizeColorBox(
    animatedSize: Dp,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(animatedSize)
            .background(color, shape = CircleShape)  // Agregar forma circular para darle un estilo atractivo
            .clickable { onClick() }
            .border(2.dp, Color.Gray, CircleShape)  // Borde para darle más énfasis visual
    )
}

// Botón para alternar entre modo claro y oscuro
@Composable
fun ModeToggleButton(isDarkMode: Boolean, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("Cambiar a ${if (isDarkMode) "Modo Claro" else "Modo Oscuro"}")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnimatedScreen() {
    AnimatedScreen()
}
