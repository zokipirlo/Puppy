/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PetsList(pets: List<Pet>, onPetSelected: (Pet) -> Unit) {
    val gridState = rememberLazyListState()
    val (fraction, setFraction) = remember { mutableStateOf(0f) }
    LazyVerticalGrid(
        state = gridState,
        cells = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(pets) { pet ->
            PetItem(pet, onPetSelected)
        }
    }
}

@Composable
fun PetItem(pet: Pet, onPetSelected: (Pet) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                onPetSelected(pet)
            }
    ) {
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
