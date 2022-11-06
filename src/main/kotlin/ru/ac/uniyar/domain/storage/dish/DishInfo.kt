package ru.ac.uniyar.domain.storage.dish

import ru.ac.uniyar.domain.storage.ingredient.Ingredient
import ru.ac.uniyar.domain.storage.restaurant.Restaurant

data class DishInfo(
    val restaurant: Restaurant,
    val ingredients: String,
    val dish: Dish,
)
