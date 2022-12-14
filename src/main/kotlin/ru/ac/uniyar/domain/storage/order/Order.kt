package ru.ac.uniyar.domain.storage.order

import com.fasterxml.jackson.databind.JsonNode
import org.http4k.core.Status
import org.http4k.format.Jackson.asJsonObject
import org.http4k.format.Jackson.asJsonValue
import ru.ac.uniyar.domain.storage.review.Review
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

data class Order(
    val id: UUID,
    val client_id: UUID,
    val restaurant_id:UUID,
    val status: String,
    val timestamp:LocalDateTime,
){
    companion object{
        fun fromJson(node: JsonNode): Order {
            val jsonObject = node.asJsonObject()
            return Order(
                UUID.fromString(jsonObject["id"].asText()),
                UUID.fromString(jsonObject["client_id"].asText()),
                UUID.fromString(jsonObject["restaurant_id"].asText()),
                jsonObject["status"].asText(),
                LocalDateTime.parse(jsonObject["timestamp"].asText(), DateTimeFormatter.ISO_LOCAL_DATE_TIME),

                )
        }
    }
    fun asJsonObject(): JsonNode {
        return listOf(
            "id" to id.toString().asJsonValue(),
            "client_id" to client_id.toString().asJsonValue(),
            "restaurant_id" to restaurant_id.toString().asJsonValue(),
            "status" to status.asJsonValue(),
            "timestamp" to timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).asJsonValue(),
        ).asJsonObject()
    }

    fun setUuid(uuid: UUID): Order {
        return this.copy(id = uuid)
    }
}
