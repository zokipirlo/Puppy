package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.R

typealias PetType =  List<Pair<String, List<String>>>

object PetData {
    val petTypes: PetType = listOf(
        "Cats" to listOf("American", "Bengal", "Calico", "Domestic", "Siamese", "Tabby"),
        "Dogs" to listOf("Boxer", "Doberman", "Husky", "Maltese", "Retriever", "Spaniel", "Shepherd", "Terrier"),
        "Rabbits" to listOf("American", "Bunny", "Californian", "Dutch", "Polish"),
        "Birds" to listOf("Chicken", "Duck", "Goose", "Turkey"),
        "Fish" to listOf("Goldfish", "Freshwater", "Saltwater")
    )

    val cats = listOf(
        Pet.Cat("Penelope", "American", 1),
        Pet.Cat("Gabby", "Bengal", 1),
        Pet.Cat("Cody", "Bengal", 2),
        Pet.Cat("Cairo", "Calico", 2),
        Pet.Cat("Gemma", "Domestic", 1),
        Pet.Cat("Tabby", "Domestic", 2),
        Pet.Cat("Tara", "Domestic", 3),
        Pet.Cat("Cleopatra", "Siamese", 1),
        Pet.Cat("Sparkle", "Siamese", 2)
    )

    val dogs = listOf(
        Pet.Dog("Rooster", "Boxer", 3),
        Pet.Dog("Geva", "Doberman", 4),
        Pet.Dog("Ozark", "Husky", 1),
        Pet.Dog("Nova", "Husky", 1),
        Pet.Dog("Stitch", "Maltese", 1),
        Pet.Dog("Jaylee", "Retriever", 2),
        Pet.Dog("Luna", "Retriever", 3),
        Pet.Dog("Charles", "Retriever", 1),
        Pet.Dog("Chase", "Shepherd", 1),
        Pet.Dog("Taz", "Terrier", 1),
        Pet.Dog("Ranger", "Terrier", 2)
    )

    val rabbits = listOf(
        Pet.Rabbit("Bowie", "Bunny", 1),
        Pet.Rabbit("Mars", "Bunny", 1),
        Pet.Rabbit("Hopper", "Dutch", 1),
        Pet.Rabbit("Nash", "Polish", 1),
    )

    val birds = listOf(
        Pet.Bird("Francis", "Chicken", 1),
        Pet.Bird("Rusty", "Duck", 1),
        Pet.Bird("Doe", "Duck", 1),
    )

    val fish = listOf(
        Pet.Fish("Karl", "Goldfish", 1),
        Pet.Fish("James", "Goldfish", 1),
        Pet.Fish("Blade", "Freshwater", 1),
        Pet.Fish("Arthur", "Freshwater", 1),
        Pet.Fish("Eore", "Freshwater", 1),
    )

    val pets = cats + dogs + rabbits + birds + fish

    fun getIconForType(type: String): Int? {
        return when (type) {
            "Cats" -> R.drawable.cat
            "Dogs" -> R.drawable.dog
            "Rabbits" -> R.drawable.rabbit
            "Birds" -> R.drawable.bird
            "Fish" -> R.drawable.fish
            else -> null
        }
    }
}
