package ru.ac.uniyar.domain.storage.restaurant

import ru.ac.uniyar.domain.storage.dish.Dish


data class RestaurantInfo(
    val restaurant: Restaurant,
    val dish: List<Dish>
)
