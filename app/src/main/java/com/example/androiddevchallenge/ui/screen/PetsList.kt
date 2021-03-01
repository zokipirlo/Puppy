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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.PetsViewModel
import com.example.androiddevchallenge.data.PetData
import com.example.androiddevchallenge.ui.widget.FilterComponent
import com.example.androiddevchallenge.ui.widget.PetsList

@Composable
fun PetsList(
    viewModel: PetsViewModel = viewModel()
) {
    val filterData by viewModel.filterData.collectAsState()
    Column {
        Surface(
            color = MaterialTheme.colors.primarySurface,
            elevation = AppBarDefaults.TopAppBarElevation
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                ) {
                    Icon(Icons.Filled.Favorite, null, tint = Color.Red)
                    Text(
                        text = "Pets looking for new love",
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                    )
                    Icon(Icons.Filled.Favorite, null, tint = Color.Red)
                }
                Spacer(modifier = Modifier.height(16.dp))
                FilterComponent(
                    selectedType = filterData.selectedType,
                    selectedBreed = filterData.selectedBreed,
                    items = PetData.petTypes,
                    onTypeSelected = {
                        viewModel.selectType(it)
                    },
                    onBreedSelected = {
                        viewModel.selectBreed(it)
                    }
                )
            }
        }
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            PetsList(viewModel.pets.collectAsState().value) {
                viewModel.openPet(it)
            }
        }
    }
}

@Preview("List")
@Composable
fun ListPreview() {
    PetsList(PetData.pets) {
    }
}
