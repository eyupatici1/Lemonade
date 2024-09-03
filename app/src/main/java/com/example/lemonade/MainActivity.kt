package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LemonadeApp() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            var currentStep by remember { mutableStateOf(1) }
            var squeezeCount by remember { mutableStateOf(0) }

            when (currentStep) {
                1 -> {
                    Lemonade(
                        textId = R.string.tree_tap,
                        imageId = R.drawable.lemon_tree,
                        descriptionId = R.string.lemon_tree,
                        onImageClick = {
                            currentStep = 2
                            squeezeCount = (2..4).random()
                        }
                    )
                }

                2 -> {
                    Lemonade(
                        textId = R.string.keep_tap,
                        imageId = R.drawable.lemon_squeeze,
                        descriptionId = R.string.lemon,
                        onImageClick = {
                            squeezeCount--
                            if (squeezeCount == 0) currentStep = 3
                        }
                    )
                }

                3 -> {
                    Lemonade(
                        textId = R.string.drink_tap,
                        imageId = R.drawable.lemon_drink,
                        descriptionId = R.string.glass,
                        onImageClick = {
                            currentStep = 4
                        }
                    )
                }

                4 -> {
                    Lemonade(
                        textId = R.string.glass_tap,
                        imageId = R.drawable.lemon_restart,
                        descriptionId = R.string.empty_glass,
                        onImageClick = {
                            currentStep = 1
                        }
                    )
                }
            }
        }

    }
}

@Composable
fun Lemonade(
    textId: Int,
    imageId: Int,
    descriptionId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.button_corner_radius)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Image(
                painter = painterResource(imageId),
                contentDescription = stringResource(descriptionId),
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.button_image_width))
                    .height(dimensionResource(id = R.dimen.button_image_height))
                    .padding(dimensionResource(id = R.dimen.button_interior_padding))
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_vertical)))
        Text(
            text = stringResource(textId),
            fontSize = 18.sp
        )
    }
}