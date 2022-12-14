package ru.ac.uniyar.domain.storage.restaurant

import com.fasterxml.jackson.databind.JsonNode
import org.http4k.format.Jackson.asJsonArray
import ru.ac.uniyar.domain.storage.EMPTY_UUID
import java.util.*

class RestaurantRepository(restaurant: Iterable<Restaurant> = emptyList()) {
    private val allRestaurants = restaurant.associateBy { it.id }.toMutableMap()

    companion object{

        fun fromJson(node: JsonNode) : RestaurantRepository {
            val allRestaurants = node.map{
                Restaurant.fromJson(it)
            }
            return RestaurantRepository(allRestaurants)
        }
    }

    fun asJsonObject(): JsonNode {
        return allRestaurants.values
            .map{ it.asJsonObject() }
            .asJsonArray()
    }

    fun fetch(id: UUID): Restaurant? = allRestaurants[id]

    fun add(restaurant: Restaurant): UUID {
        var newId = restaurant.id
        while (allRestaurants.containsKey(newId) || newId == EMPTY_UUID){
            newId = UUID.randomUUID()
        }
        allRestaurants[newId] = restaurant.setUuid(newId)
        return newId
    }

    fun list() = allRestaurants.values.toList()
}