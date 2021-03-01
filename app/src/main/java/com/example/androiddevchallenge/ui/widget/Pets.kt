package com.example.androiddevchallenge.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Pet
import com.example.androiddevchallenge.data.PetData
import com.kyant.pixelmusic.ui.sharedelements.SharedElement

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PetsList(pets: List<Pet>, onPetSelected: (Pet) -> Unit) {
    val gridState = rememberLazyListState()
    val (fraction, setFraction) = remember { mutableStateOf(0f) }
    LazyVerticalGrid(
        state = gridState,
        cells = GridCells.Adaptive(minSize = 128.dp),
        modifier = Modifier
            .padding(4.dp)
    ) {
        items(pets) { pet ->
            SharedElement(
                pet.name, "pet",
//                shape = RoundedCornerShape(24.dp * fraction),
//                transitionSpec = MaterialContainerTransformSpec(
//                    pathMotionFactory = MaterialArcMotionFactory,
//                    durationMillis = 600,
//                    fadeMode = FadeMode.In
//                ),
//                onFractionChanged = setFraction
            ) {
                PetItem(pet, onPetSelected)
            }
        }
    }
}

@Composable
fun PetItem(pet: Pet, onPetSelected: (Pet) -> Unit) {
    Card(modifier = Modifier
        .padding(4.dp)
        .clickable {
            onPetSelected(pet)
        }) {
        Column(
            modifier = Modifier
                .width(150.dp)
                .height(130.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .height(80.dp)
            ) {
                val icon = PetData.getIconForType(pet.type)
                if (icon != null) {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = pet.type,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .width(48.dp)
                            .height(48.dp)
                    )
                }
            }
            Text(
                style = MaterialTheme.typography.body1,
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
                style = MaterialTheme.typography.caption,
                text = pet.breed,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(
                        top = 4.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                    .fillMaxWidth()
            )
        }
    }
}

@Preview("Cat")
@Composable
fun PetItemPreview() {
    PetItem(pet = Pet.Cat("Esmeralda", "Siam", 1)) {

    }
}