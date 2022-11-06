package ru.ac.uniyar.domain.storage.ingredient

import com.fasterxml.jackson.databind.JsonNode
import org.http4k.format.Jackson.asJsonObject
import org.http4k.format.Jackson.asJsonValue
import java.util.*

data class Ingredient(
    val id : UUID,
    val nameIngredient : String,
){
    companion object{
        fun fromJson(node: JsonNode): Ingredient {
            val jsonObject = node.asJsonObject()
            return Ingredient(
                UUID.fromString(jsonObject["id"].asText()),
                jsonObject["nameIngredient"].asText(),

            )
        }
    }
    fun asJsonObject(): JsonNode {
        return listOf(
            "id" to id.toString().asJsonValue(),
            "nameIngredient" to nameIngredient.asJsonValue(),
        ).asJsonObject()
    }
    fun setUuid(uuid: UUID): Ingredient {
        return this.copy(id = uuid)
    }

}
