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