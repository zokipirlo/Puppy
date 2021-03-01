package com.example.androiddevchallenge.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Pet
import com.example.androiddevchallenge.data.PetData
import com.kyant.pixelmusic.ui.sharedelements.SharedElement
import com.kyant.pixelmusic.ui.sharedelements.SharedMaterialContainer

@Composable
fun PetDetails(pet: Pet, onClose: () -> Unit) {
    BackHandler(onBack = onClose)
    SharedElement(pet.name, "pet") {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column {
                IconButton(onClick = { onClose() }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ) {
                    val icon = PetData.getIconForType(pet.type)
                    if (icon != null) {
                        Icon(
                            painter = painterResource(icon),
                            contentDescription = pet.type,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .width(80.dp)
                                .height(80.dp)
                        )
                    }
                }
                Text(
                    style = MaterialTheme.typography.h4,
                    text = pet.name,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(
                            top = 0.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                        .fillMaxWidth()
                )
                Text(
                    style = MaterialTheme.typography.subtitle1,
                    text = pet.breed,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(
                            top = 8.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                        .fillMaxWidth()
                )
                Text(
                    style = MaterialTheme.typography.subtitle2,
                    text = ageText(pet.age),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(
                            top = 6.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                        .fillMaxWidth()
                )
            }
        }
    }
}

private fun ageText(age: Int): String = when (age) {
    1 -> "$age year old"
    else -> "$age years old"
}

@Preview("Cat")
@Composable
fun PetItemPreview() {
    PetDetails(pet = Pet.Cat("Esmeralda", "Siam", 1)) {

    }
}