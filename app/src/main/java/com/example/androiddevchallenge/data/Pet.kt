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
package com.example.androiddevchallenge.data

sealed class Pet(
    val type: String,
    val name: String,
    val breed: String,
    val age: Int
) {
    class Cat(name: String, breed: String, age: Int) : Pet("Cats", name, breed, age)
    class Dog(name: String, breed: String, age: Int) : Pet("Dogs", name, breed, age)
    class Rabbit(name: String, breed: String, age: Int) : Pet("Rabbits", name, breed, age)
    class Bird(name: String, breed: String, age: Int) : Pet("Birds", name, breed, age)
    class Fish(name: String, breed: String, age: Int) : Pet("Fish", name, breed, age)
}
