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
package com.example.androiddevchallenge.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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

@Composable
fun PetDetails(pet: Pet, onClose: () -> Unit) {
    BackHandler(onBack = onClose)
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
