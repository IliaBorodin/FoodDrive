package ru.ac.uniyar.domain.storage.dish

import com.fasterxml.jackson.databind.JsonNode
import org.http4k.format.Jackson.asJsonArray
import ru.ac.uniyar.domain.storage.EMPTY_UUID
import java.util.*

class DishRepository(dish: Iterable<Dish> = emptyList()) {
    private val allDishes = dish.associateBy { it.id }.toMutableMap()

    companion object{
        fun fromJson(node: JsonNode) : DishRepository {
            val allDishes = node.map{
                Dish.fromJson(it)
            }
            return DishRepository(allDishes)
        }
    }

    fun asJsonObject(): JsonNode {
        return allDishes.values
            .map{ it.asJsonObject() }
            .asJsonArray()
    }

    fun fetch(id: UUID): Dish? = allDishes[id]

    fun add(dish: Dish): UUID {
        var newId = dish.id
        while (allDishes.containsKey(newId) || newId == EMPTY_UUID){
            newId = UUID.randomUUID()
        }
        allDishes[newId] = dish.setUuid(newId)
        return newId
    }

    fun delete(id: UUID) {
        allDishes.remove(id)
    }

    fun edit(dish: Dish){
        allDishes[dish.id] = dish
    }

    fun list() = allDishes.values.toList()
}