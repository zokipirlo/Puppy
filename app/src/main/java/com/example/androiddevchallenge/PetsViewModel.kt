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
package com.example.androiddevchallenge

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.data.Pet
import com.example.androiddevchallenge.data.PetData
import com.example.androiddevchallenge.data.PetFilterData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PetsViewModel : ViewModel() {
    private val _pets = MutableStateFlow(PetData.pets)
    val pets: StateFlow<List<Pet>> = _pets

    private val _filterData = MutableStateFlow(PetFilterData(null, null))
    val filterData: StateFlow<PetFilterData> = _filterData

    private val _pet = MutableStateFlow<Pet?>(null)
    val pet: StateFlow<Pet?> = _pet

    fun selectType(type: String?) {
        _filterData.value = _filterData.value.copy(
            selectedType = type
        )
        selectBreed(null)
        updatePets()
    }

    fun selectBreed(breed: String?) {
        _filterData.value = _filterData.value.copy(
            selectedBreed = breed
        )
        updatePets()
    }

    private fun updatePets() {
        val filterData = _filterData.value
        _pets.value = when {
            filterData.selectedType == null -> {
                PetData.pets
            }
            filterData.selectedBreed != null -> {
                val newData =
                    PetData.pets.filter { it.type == filterData.selectedType && it.breed == filterData.selectedBreed }
                Log.d("PetsViewModel", "$newData")
                newData
            }
            else -> {
                val newData = PetData.pets.filter { it.type == filterData.selectedType }
                Log.d("PetsViewModel", "${filterData.selectedType} $newData")
                newData
            }
        }
    }

    fun openPet(pet: Pet) {
        _pet.value = pet
    }

    fun closePet() {
        _pet.value = null
    }
}
