package com.example.lab13animaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //AnimatedVisibility()
            //AnimateColor()
            AnimateSizeAndPosition()
        }
    }
}

@Composable
fun AnimatedVisibility() {
    var isVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { isVisible = !isVisible }) {
                Text(text = if (isVisible) "Ocultar" else "Mostrar")
            }
            Spacer(modifier = Modifier.height(20.dp))
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Blue)
                )
            }
        }
    }
}

@Composable
fun AnimateColor() {
    var isBlue by remember { mutableStateOf(true) }
    val color by animateColorAsState(targetValue = if (isBlue) Color.Blue else Color.Green,
        label = ""
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { isBlue = !isBlue }) {
            Text(text = "Cambio de Color")
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color)
                .size(100.dp)
        )
    }
}


@Composable
fun AnimateSizeAndPosition() {
    var isLarge by remember { mutableStateOf(false) }
    var moveDirection by remember { mutableIntStateOf(0) }

    val size by animateDpAsState(targetValue = if (isLarge) 200.dp else 100.dp, label = "")

    val directions = listOf(
        Pair(200.dp, 0.dp),
        Pair(0.dp, 200.dp),
        Pair((-200).dp, 0.dp),
        Pair(0.dp, (-200).dp)
    )

    val (offsetX, offsetY) = directions[moveDirection % directions.size]

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            isLarge = !isLarge
            moveDirection += 1
        }) {
            Text(text = "Cambiar Tamaño y Mover")
        }

        Box(
            modifier = Modifier
                .size(size)
                .offset(x = offsetX, y = offsetY)
                .background(Color.Red)
        )
    }
}






