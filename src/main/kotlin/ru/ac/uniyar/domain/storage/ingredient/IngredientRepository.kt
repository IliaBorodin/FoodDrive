package ru.ac.uniyar.domain.storage.ingredient

import com.fasterxml.jackson.databind.JsonNode
import org.http4k.format.Jackson.asJsonArray
import ru.ac.uniyar.domain.storage.EMPTY_UUID
import java.util.*

class IngredientRepository(ingredient: Iterable<Ingredient> = emptyList()) {
    private val allIngredients = ingredient.associateBy { it.id }.toMutableMap()

    companion object{

        fun fromJson(node: JsonNode) : IngredientRepository {
            val allIngredients = node.map{
                Ingredient.fromJson(it)
            }
            return IngredientRepository(allIngredients)
        }
    }

    fun asJsonObject(): JsonNode {
        return allIngredients.values
            .map{ it.asJsonObject() }
            .asJsonArray()
    }

    fun fetch(id: UUID): Ingredient? = allIngredients[id]

    fun add(ingredient: Ingredient): UUID {
        var newId = ingredient.id
        while (allIngredients.containsKey(newId) || newId == EMPTY_UUID){
            newId = UUID.randomUUID()
        }
        allIngredients[newId] = ingredient.setUuid(newId)
        return newId
    }

    fun list() = allIngredients.values.toList()

    fun delete(id: UUID) {
        allIngredients.remove(id)
    }

    fun edit(ingredient: Ingredient){
        allIngredients[ingredient.id] = ingredient
    }

    fun getAllIngredients() : Map<UUID, Ingredient>{
        return allIngredients
    }
}